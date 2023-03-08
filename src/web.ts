import { WebPlugin } from '@capacitor/core';

import type { CheckOverlayAppsResult, CheckOverlayPlugin } from './definitions';

export class CheckOverlayWeb extends WebPlugin implements CheckOverlayPlugin {
  async checkOverlayApps(options: { blackList: Array<string> }): Promise<CheckOverlayAppsResult> {
    throw new Error('Method not implemented.'+options);
  }
}
