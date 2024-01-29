import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from 'apps/frontend/src/environments/environment';
import { Ad, AdPagination, AdWithOwner } from '../../interfaces/advertisement';

@Injectable({
  providedIn: 'root',
})
export class AdService {
  private api = environment.api;

  private http = inject(HttpClient);

  public getAdById(id: string) {
    return this.http.get<Ad | AdWithOwner>(`${this.api}/ad/${id}`);
  }

  public getAdsWithPagination(page: string, size: string) {
    return this.http.get<AdPagination>(
      `${this.api}/ad?page=${page}&size=${size}`
    );
  }

  public searchAdsByTitle(title: string, page: string, size: string) {
    return this.http.get<AdPagination>(
      `${this.api}/ad/search?title=${title}&page=${page}&size${size}`
    );
  }
}
