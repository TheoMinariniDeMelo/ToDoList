import { ResolveFn } from '@angular/router';

export const routeResolver: ResolveFn<boolean> = (route, state) => {
  return true;
};
