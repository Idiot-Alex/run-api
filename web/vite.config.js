import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import Unocss from 'unocss/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import AutoImport from 'unplugin-auto-import/vite'
import pluginPlayWright from './src/plugins/rollup-plugin-playwright'

// https://vitejs.dev/config/
export default defineConfig({
  define: { 
    'process.versions':  process.versions
  },
  plugins: [
    vue(),
    Unocss({ /* options */ }),
    Components({
      resolvers: [
        ElementPlusResolver(),
      ],
      dts: true,
      include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
    }),
    AutoImport({
      resolvers: [
          ElementPlusResolver()
      ],
      // targets to transform
      include: [
        /\.[tj]sx?$/, // .ts, .tsx, .js, .jsx
        /\.vue$/, /\.vue\?vue/, // .vue
        /\.md$/, // .md
      ],
    }),
    pluginPlayWright(),
  ]
})
