import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DividerModule } from 'primeng/divider';

@Component({
  selector: 'pet-market-footer',
  standalone: true,
  imports: [CommonModule, DividerModule],
  template: `
    <footer class="wrapper">
      <div class="content">
        <p-divider></p-divider>
        <p>
          Website made by
          <a href="https://fabricioflores.se" target="_blank"
            >Fabricio Flores</a
          >
        </p>
      </div>
    </footer>
  `,
})
export class FooterComponent {}
