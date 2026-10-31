$ErrorActionPreference = 'Stop'
& "$PSScriptRoot\build.ps1"
. "$PSScriptRoot\scripts\java-tools.ps1"
Push-Location $PSScriptRoot
try {
    New-Item -ItemType Directory -Force build/test-classes | Out-Null
    & $JavacExe --release 17 -encoding UTF-8 -cp "bin;lib/Pokemon.jar" -d build/test-classes tests/Lab2Test.java
    if ($LASTEXITCODE -ne 0) { throw 'Test compilation failed.' }
    & $JavaExe -cp "build/test-classes;bin;lib/Pokemon.jar" Lab2Test
    if ($LASTEXITCODE -ne 0) { throw 'Tests failed.' }
} finally { Pop-Location }

