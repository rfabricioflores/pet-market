import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const isLoggedIn = authService.isLoggedIn();

  if (state.url === '/register' || state.url === '/login') {
    if (isLoggedIn) {
      router.navigate(['profile']);
      return false;
    }
    return true;
  } else {
    if (isLoggedIn) {
      return true;
    }

    router.navigate(['/login']);
    return false;
  }
};
