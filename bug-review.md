# Bug Review

## Bug: Typo in error message

**File:** `src/main/kotlin/org/athenian/lambdas/7_OverloadedOps.kt`, line 19

**Description:** The error message for the `"eat"` command contains a typo — `"Missing eateat arg"` should be
`"Missing eat arg"`. The duplicated word is inconsistent with the pattern used by the `"go"` command on the preceding
line, which correctly says `"Missing go arg"`.

**Current code:**

```kotlin
"eat" -> if (parts.size > 1) "eating ${parts[1]}" else "Missing eateat arg"
```

**Fix:**

```kotlin
"eat" -> if (parts.size > 1) "eating ${parts[1]}" else "Missing eat arg"
```
