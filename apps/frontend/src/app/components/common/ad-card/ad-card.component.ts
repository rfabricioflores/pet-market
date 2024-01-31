import { Component, input } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { Ad } from '../../../interfaces/advertisement';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'pet-market-ad-card',
  standalone: true,
  imports: [CommonModule, DatePipe, RouterLink],
  templateUrl: './ad-card.component.html',
  styleUrl: './ad-card.component.scss',
})
export class AdCardComponent {
  public ad = input.required<Ad>();
}
