import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchComponent } from '../../components/common/search/search.component';

@Component({
  selector: 'pet-market-home',
  standalone: true,
  imports: [CommonModule, SearchComponent],
  templateUrl: './home.component.html',
})
export class HomeComponent {}
