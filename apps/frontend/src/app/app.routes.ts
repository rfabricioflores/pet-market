import { Route } from '@angular/router';

export const appRoutes: Route[] = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/home/home.component').then((m) => m.HomeComponent),
  },
  {
    path: 'adverts',
    loadComponent: () =>
      import('./pages/adverts/adverts.component').then(
        (m) => m.AdvertsComponent
      ),
  },
];
