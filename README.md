# capacitor-plugin-check-overlay

This plugin is an Android plugin that checks for any installed overlay apps on the device. It can be used to enhance user's security by providing a warning if any apps are granted permission to draw overlay layers above other apps on the screen.

## Install

```bash
npm install capacitor-plugin-check-overlay
npx cap sync
```

## API

<docgen-index>

* [`checkOverlayApps(...)`](#checkoverlayapps)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### checkOverlayApps(...)

```typescript
checkOverlayApps(options: { blackList: Array<string>; }) => Promise<CheckOverlayAppsResult>
```

| Param         | Type                                  | Description                                  |
| ------------- | ------------------------------------- | -------------------------------------------- |
| **`options`** | <code>{ blackList: string[]; }</code> | : <a href="#array">Array</a>&lt;string&gt; } |

**Returns:** <code>Promise&lt;<a href="#checkoverlayappsresult">CheckOverlayAppsResult</a>&gt;</code>

--------------------


### Interfaces


#### CheckOverlayAppsResult

| Prop              | Type                                                  |
| ----------------- | ----------------------------------------------------- |
| **`hasOverlay`**  | <code>boolean</code>                                  |
| **`message`**     | <code>string</code>                                   |
| **`overlayApps`** | <code><a href="#array">Array</a>&lt;string&gt;</code> |

</docgen-api>
