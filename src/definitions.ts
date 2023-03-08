export interface CheckOverlayPlugin {

  /**
   * 
   * @param options { blackList: Array<string> }
   */
  checkOverlayApps(options: { blackList: Array<string> }): Promise<CheckOverlayAppsResult>;
}

export interface CheckOverlayAppsResult {
  hasOverlay: boolean;
  message?: string;
  overlayApps?: Array<string>;
}