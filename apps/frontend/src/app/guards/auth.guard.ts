import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';
import { MessageService } from 'primeng/api';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const messageService = inject(MessageService);
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
    messageService.add({
      severity: 'warn',
      detail: 'Du måste först logga in för att komma åt denna sida',
      summary: 'Ej behörig!',
      life: 8000,
    });
    return false;
  }
};
