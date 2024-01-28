import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './components/layout/navbar/navbar.component';
import { FooterComponent } from './components/layout/footer/footer.component';

@Component({
  standalone: true,
  selector: 'pet-market-root',
  template: `
    <pet-market-navbar />
    <router-outlet />
    <pet-market-footer />
  `,
  imports: [RouterModule, NavbarComponent, FooterComponent],
})
export class AppComponent {
  title = 'Pet Market';
}
