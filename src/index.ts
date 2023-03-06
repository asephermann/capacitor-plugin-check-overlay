import { registerPlugin } from '@capacitor/core';

import type { CheckOverlayPlugin } from './definitions';

const CheckOverlay = registerPlugin<CheckOverlayPlugin>('CheckOverlay', {
  web: () => import('./web').then(m => new m.CheckOverlayWeb()),
});

export * from './definitions';
export { CheckOverlay };
