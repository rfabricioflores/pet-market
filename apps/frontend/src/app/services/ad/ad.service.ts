import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Ad, AdForm, AdPagination } from '../../interfaces/advertisement';

@Injectable({
  providedIn: 'root',
})
export class AdService {
  private api = environment.api;

  private http = inject(HttpClient);

  public getAdById(id: string) {
    return this.http.get<Ad>(`${this.api}/ad/${id}`);
  }

  public getAdsWithPagination(page: number, size: number) {
    return this.http.get<AdPagination>(
      `${this.api}/ad?page=${page}&size=${size}`
    );
  }

  public searchAdsByTitle(title: string, page: number, size: number) {
    return this.http.get<AdPagination>(
      `${this.api}/ad/search?title=${title}&page=${page}&size${size}`
    );
  }

  public createAd(ad: AdForm) {
    return this.http.post<Ad>(`${this.api}/ad`, ad);
  }
}
