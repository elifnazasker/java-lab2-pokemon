$ErrorActionPreference = 'Stop'
& "$PSScriptRoot\build.ps1"
. "$PSScriptRoot\scripts\java-tools.ps1"
& $JavaExe -jar "$PSScriptRoot\dist\Lab2Pokemon.jar"
if ($LASTEXITCODE -ne 0) { throw 'Battle failed.' }

