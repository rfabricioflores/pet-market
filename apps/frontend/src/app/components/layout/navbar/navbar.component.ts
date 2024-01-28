import { Component, HostBinding } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';

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

  public items: MenuItem[];

  constructor() {
    this.items = [
      {
        label: 'Lägg till annons',
        icon: 'pi pi-plus',
      },
      {
        label: 'Annonser',
        icon: 'pi pi-shopping-bag',
        routerLink: 'adverts',
      },
      {
        label: 'Logga in',
        icon: 'pi pi-user',
        routerLink: 'login',
      },
    ];
  }
}
