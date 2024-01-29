import { Component, HostBinding, effect, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'pet-market-navbar',
  standalone: true,
  imports: [CommonModule, MenubarModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  // Adds class wrapper to the selector tag
  @HostBinding('class')
  readonly class = 'wrapper';

  public items: MenuItem[] = [];

  private allItems: MenuItem[] = [
    {
      label: 'Lägg till annons',
      icon: 'pi pi-plus',
      routerLink: 'adverts/create',
    },
    {
      label: 'Annonser',
      icon: 'pi pi-search',
      routerLink: 'adverts',
    },
    {
      label: 'Logga in',
      icon: 'pi pi-user',
      routerLink: 'login',
    },
    {
      label: 'Profil',
      icon: 'pi pi-user',
      routerLink: 'profile',
    },
  ];

  private authService = inject(AuthService);

  constructor() {
    effect(() => {
      if (this.authService.user()) {
        this.items = this.allItems.filter(
          (item) => item.routerLink !== 'login'
        );
      } else {
        this.items = this.allItems.filter(
          (item) => item.routerLink !== 'profile'
        );
      }
    });
  }
}
