import { Component, HostBinding, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth/auth.service';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'pet-market-profile',
  standalone: true,
  imports: [CommonModule, ButtonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
})
export class ProfileComponent {
  @HostBinding('class')
  readonly class = 'wrapper';

  constructor() {
    this.user = this.authService.user;
  }

  public user;

  private authService = inject(AuthService);

  public logout() {
    this.authService.logout();
  }
}
