import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  imports: [RouterModule],
  selector: 'pet-marketplace-root',
  template: `
    <p>Welcome Pet Market</p>
    <router-outlet></router-outlet>
  `,
})
export class AppComponent {
  title = 'Pet Market';
}
