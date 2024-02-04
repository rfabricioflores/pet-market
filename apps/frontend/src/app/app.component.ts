import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './components/layout/navbar/navbar.component';
import { FooterComponent } from './components/layout/footer/footer.component';
import { ToastModule } from 'primeng/toast';

@Component({
  standalone: true,
  selector: 'pet-market-root',
  template: `
    <pet-market-navbar />
    <router-outlet />
    <pet-market-footer />
    <p-toast />
  `,
  imports: [RouterModule, NavbarComponent, FooterComponent, ToastModule],
})
export class AppComponent {
  title = 'Pet Market';
}
