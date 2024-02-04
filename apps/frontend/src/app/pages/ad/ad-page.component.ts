import {
  Component,
  HostBinding,
  OnInit,
  Signal,
  computed,
  inject,
  input,
  signal,
} from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
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
    DatePipe,
  ],
  templateUrl: './ad-page.component.html',
  styleUrl: './ad-page.component.scss',
})
export class AdPageComponent implements OnInit {
  @HostBinding('class')
  readonly class = 'wrapper';

  private adService = inject(AdService);

  private id = input<string>();
  public error = signal(false);
  public ad = signal<Ad | null>(null);
  public adPhotos: Signal<unknown[] | undefined> = computed(() =>
    this.ad()?.photos.map((item) => ({ itemImageSrc: item.url }))
  );

  ngOnInit() {
    this.adService.getAdById(this.id()!).subscribe({
      next: (ad) => this.ad.set(ad),
      error: () => this.error.set(true),
    });
  }
}
