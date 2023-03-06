import { WebPlugin } from '@capacitor/core';

import type { CheckOverlayPlugin } from './definitions';

export class CheckOverlayWeb extends WebPlugin implements CheckOverlayPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
