import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchComponent } from '../../components/common/search/search.component';
import { AdPagination } from '../../interfaces/advertisement';
import { AdService } from '../../services/ad/ad.service';
import { AdCardListComponent } from '../../components/common/ad-card-list/ad-card-list.component';
import { PaginatorModule } from 'primeng/paginator';

interface PageEvent {
  first: number;
  rows: number;
  page: number;
  pageCount: number;
}

@Component({
  selector: 'pet-market-home',
  standalone: true,
  imports: [
    CommonModule,
    SearchComponent,
    AdCardListComponent,
    PaginatorModule,
  ],
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  private adService = inject(AdService);

  public adList = signal<AdPagination | null>(null);

  ngOnInit() {
    this.loadAds(0, 10);
  }

  private loadAds(page: number, size: number) {
    this.adService.getAdsWithPagination(page, size).subscribe({
      next: (data) => this.adList.set(data),
      error: () => this.adList.set(null),
    });
  }

  public first: number = 0;
  public rows: number = 10;

  onPageChange(event: unknown) {
    const e = event as PageEvent;
    this.first = e.first;
    this.rows = e.rows;
    console.log(e.page, this.rows);
    this.loadAds(e.page, this.rows);
  }
}
