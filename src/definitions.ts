export interface CheckOverlayPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
