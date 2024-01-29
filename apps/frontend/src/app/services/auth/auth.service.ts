import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from '../../../environments/environment';
import { RegisterForm, RegisterResponse } from '../../interfaces/register';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private api = environment.api;

  private http = inject(HttpClient);

  public register(form: RegisterForm) {
    return this.http.post<RegisterResponse>(`${this.api}/register`, form);
  }
}
