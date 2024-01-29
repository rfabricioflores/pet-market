import { Component, HostBinding, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { AuthService } from '../../services/auth/auth.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'pet-market-login',
  standalone: true,
  imports: [
    CommonModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  @HostBinding('class')
  readonly class = 'wrapper';

  private authService = inject(AuthService);

  // Form group creation
  public form = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  public formField(name: string) {
    return this.form.get(name);
  }

  public loading = signal(false);
  public errorMessage = signal<string | null>(null);

  // Submit Form
  public submit() {
    if (this.form.valid) {
      this.authService
        .login({
          username: this.formField('username')!.value,
          password: this.formField('password')!.value,
        })
        .subscribe({
          error: () =>
            this.errorMessage.set('Ett fel inträffade, försök igen!'),
        });
    }
  }
}
