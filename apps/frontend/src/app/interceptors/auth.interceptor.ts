import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('app_token');
  const authService = inject(AuthService);

  if (token) {
    const cloned = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + token),
    });

    return next(cloned).pipe(
      catchError((err) => {
        if ([403].includes(err.status)) {
          authService.logout();
        }
        return throwError(() => err);
      })
    );
  }

  return next(req);
};
