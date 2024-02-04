import { Component, HostBinding, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchComponent } from '../../components/common/search/search.component';
import { AdPagination } from '../../interfaces/advertisement';
import { AdService } from '../../services/ad/ad.service';
import { AdCardListComponent } from '../../components/common/ad-card-list/ad-card-list.component';
import { PaginatorModule } from 'primeng/paginator';
import { PaginatorState } from 'primeng/paginator/paginator.interface';

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
  styles: `
  :host {
    display: block;
  }
  `,
})
export class HomeComponent implements OnInit {
  @HostBinding('class')
  readonly class = 'wrapper';

  private adService = inject(AdService);

  public adList = signal<AdPagination | null>(null);

  public first = 0;
  private page = 0;
  public size = 10;

  ngOnInit() {
    this.loadAds();
  }

  private loadAds() {
    this.adService.getAdsWithPagination(this.page, this.size).subscribe({
      next: (data) => this.adList.set(data),
      error: () => this.adList.set(null),
    });
  }

  public onPageChange(event: PaginatorState) {
    event.first && (this.first = event.first);
    event.rows && (this.size = event.rows);
    event.page && (this.page = event.page);

    this.loadAds();
  }
}
