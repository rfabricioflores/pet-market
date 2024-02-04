import { Component, HostBinding, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { AdService } from '../../services/ad/ad.service';
import { AdForm } from '../../interfaces/advertisement';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'pet-market-create-ad',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InputTextModule,
    ButtonModule,
    RouterLink,
  ],
  templateUrl: './create-ad.component.html',
  styleUrl: './create-ad.component.scss',
})
export class CreateAdComponent {
  @HostBinding('class')
  readonly class = 'wrapper';

  private adService = inject(AdService);

  public form = new FormGroup({
    title: new FormControl(''),
    description: new FormControl(''),
    price: new FormControl(''),
    photoOne: new FormControl(''),
    photoTwo: new FormControl(''),
    photoThree: new FormControl(''),
  });

  public formField(name: string) {
    return this.form.get(name);
  }

  public successId = signal<number | null>(null);
  public loading = signal(false);
  public errorMessage = signal<string | null>(null);

  public submit() {
    if (this.form.valid) {
      this.loading.set(true);

      const data: AdForm = {
        title: this.formField('title')!.value,
        description: this.formField('description')!.value,
        price: this.formField('price')!.value,
        photos: [],
      };

      this.formField('photoOne')?.value.length > 10 &&
        data.photos.push(this.formField('photoOne')?.value);
      this.formField('photoTwo')?.value.length > 10 &&
        data.photos.push(this.formField('photoTwo')?.value);
      this.formField('photoThree')?.value.length > 10 &&
        data.photos.push(this.formField('photoThree')?.value);

      this.adService.createAd(data).subscribe({
        next: (res) => {
          this.successId.set(res.id);
          this.loading.set(false);
        },
        error: () => {
          this.errorMessage.set('Ett fel inträffade, försök igen!');
          this.loading.set(false);
        },
      });
    }
  }
}
