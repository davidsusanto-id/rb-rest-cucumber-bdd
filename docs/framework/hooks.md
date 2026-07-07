# Hooks

## Why I put `Hooks.java` inside `steps/`?

Because Cucumber's glue is a flat classpath scan, not a directory convention. The `cucumber.glue` value points at a 
package (`io.davidsusanto.restfulbooker.steps`), and Cucumber loads every glue class -- step definitions and hook classes (`@Before`,
`@BeforeAll`, `@After`) -- from that package and its subpackages. Hooks aren't special; they're just glue with 
lifecycle annotations instead of step annotations.

So placement is driven by one rule: **the class must be under the glue package to be discovered**. `steps` is the glue
package, so `Hooks.java` goes there. If I'd put it in `io.davidsusanto.restfulbooker.config` or 
`io.davidsusanto.restfulbooker.utils`, those aren't scanned as glue, and the hooks would silently never fire -- 
no error, just skipped setup. That failure mode is easy to miss and annoying to debug.