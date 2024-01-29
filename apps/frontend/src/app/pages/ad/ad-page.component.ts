import {
  Component,
  HostBinding,
  OnInit,
  inject,
  input,
  signal,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdService } from '../../services/ad/ad.service';
import { Ad } from '../../interfaces/advertisement';
import { DividerModule } from 'primeng/divider';
import { AvatarModule } from 'primeng/avatar';
import { GalleriaModule } from 'primeng/galleria';
import { ButtonModule } from 'primeng/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'pet-market-ad-page',
  standalone: true,
  imports: [
    CommonModule,
    DividerModule,
    AvatarModule,
    GalleriaModule,
    ButtonModule,
    RouterLink,
  ],
  templateUrl: './ad-page.component.html',
  styleUrl: './ad-page.component.scss',
})
export class AdPageComponent implements OnInit {
  @HostBinding('class')
  readonly class = 'wrapper';

  images: galleriImage[] = [];

  private adService = inject(AdService);

  private id = input<string>();
  public error = signal(false);
  public ad = signal<Ad | null>(null);

  ngOnInit() {
    this.adService.getAdById(this.id()!).subscribe({
      next: (ad) => {
        this.ad.set(ad);

        if (this.ad()?.photos.length !== 0) {
          this.ad()?.photos.forEach(({ url }) => {
            this.images.push({
              itemImageSrc: url,
            });
          });
        } else {
          this.images.push({
            itemImageSrc: '/assets/no-image.png',
          });
        }
      },
      error: () => this.error.set(true),
    });
  }
}

interface galleriImage {
  itemImageSrc: string;
}
