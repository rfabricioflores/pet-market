import { Component, HostBinding, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { RegisterForm, RegisterResponse } from '../../interfaces/register';

@Component({
  selector: 'pet-market-register',
  standalone: true,
  imports: [
    CommonModule,
    InputTextModule,
    ReactiveFormsModule,
    ButtonModule,
    RouterLink,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {
  @HostBinding('class')
  readonly class = 'wrapper';

  private authService = inject(AuthService);

  // Form group creation
  public form = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    phone: new FormControl(''),
    email: new FormControl(''),
  });

  public formField(name: string) {
    return this.form.get(name);
  }

  public loading = signal(false);
  public errorMessage = signal<string | null>(null);
  public response = signal<RegisterResponse | null>(null);

  // Submit Form
  public submit() {
    this.errorMessage.set(null);

    if (this.form.valid) {
      const formValue: RegisterForm = {
        username: this.formField('username')!.value,
        password: this.formField('password')!.value,
        firstname: this.formField('firstname')!.value,
        lastname: this.formField('lastname')!.value,
        phone: this.formField('phone')?.value || null,
        email: this.formField('email')!.value,
      };

      this.loading.set(true);

      this.authService.register(formValue).subscribe({
        next: (res) => this.response.set(res),
        error: () => {
          this.errorMessage.set('Ett fel inträffade, försök igen!');
          this.loading.set(false);
        },
        complete: () => this.loading.set(false),
      });
    }
  }
}
