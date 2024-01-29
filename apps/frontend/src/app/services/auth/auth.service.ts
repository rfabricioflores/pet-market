import { HttpClient } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { environment } from '../../../environments/environment';
import { RegisterForm, RegisterResponse } from '../../interfaces/register';
import { LoginForm, LoginResponse } from '../../interfaces/login';
import { tap } from 'rxjs';
import { AuthenticatedUser } from '../../interfaces/auth';
import { Router } from '@angular/router';
import moment from 'moment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private api = environment.api;

  private http = inject(HttpClient);
  private router = inject(Router);

  public user = signal<AuthenticatedUser | null>(null);

  constructor() {
    if (!this.tokenIsExpired()) {
      const userData = JSON.parse(localStorage.getItem('auth_user')!) as
        | AuthenticatedUser
        | null
        | undefined;

      if (userData) {
        this.user.set(userData);
      }
    } else {
      this.clearLocalStorage();
    }
  }

  public register(form: RegisterForm) {
    return this.http.post<RegisterResponse>(`${this.api}/register`, form);
  }

  public login(form: LoginForm) {
    return this.http
      .post<LoginResponse>(`${this.api}/login`, form, { observe: 'response' })
      .pipe(
        tap({
          next: (data) => {
            if (data.body) {
              this.saveToLocalStorage(
                data.body.token,
                data.body.expDate,
                data.body.user
              );

              this.user.set(data.body.user);
              this.router.navigate(['/profile']);
            }
          },
        })
      );
  }

  public logout() {
    this.router.navigate(['']);
    this.user.set(null);
    this.clearLocalStorage();
  }

  private clearLocalStorage() {
    localStorage.removeItem('app_token');
    localStorage.removeItem('token_expDate');
    localStorage.removeItem('auth_user');
  }

  public isLoggedIn(): boolean {
    return (
      !this.tokenIsExpired() &&
      localStorage.getItem('auth_user') != null &&
      localStorage.getItem('app_token') != null &&
      this.user() != null
    );
  }

  private saveToLocalStorage(
    token: string,
    expDate: string,
    user: AuthenticatedUser
  ) {
    localStorage.setItem('app_token', token);
    localStorage.setItem('token_expDate', expDate);
    localStorage.setItem('auth_user', JSON.stringify(user));
  }

  private tokenIsExpired(): boolean {
    const expDateUtc = localStorage.getItem('token_expDate');

    if (expDateUtc) {
      const now = moment(new Date());
      const expDate = moment(new Date(expDateUtc));

      if (expDate.isBefore(now)) return true;
      return false;
    }
    return true;
  }
}
