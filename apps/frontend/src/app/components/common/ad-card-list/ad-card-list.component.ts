import { Component, input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdPagination } from '../../../interfaces/advertisement';
import { AdCardComponent } from '../ad-card/ad-card.component';
import { PaginatorModule } from 'primeng/paginator';
import { DividerModule } from 'primeng/divider';

@Component({
  selector: 'pet-market-ad-card-list',
  standalone: true,
  imports: [CommonModule, AdCardComponent, PaginatorModule, DividerModule],
  templateUrl: './ad-card-list.component.html',
  styleUrl: './ad-card-list.component.scss',
})
export class AdCardListComponent {
  public adList = input.required<AdPagination>();
}
