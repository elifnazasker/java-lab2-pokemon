$ErrorActionPreference = 'Stop'
. "$PSScriptRoot\scripts\java-tools.ps1"
Push-Location $PSScriptRoot
try {
    $classDir = [IO.Path]::GetFullPath((Join-Path $PSScriptRoot 'bin'))
    if ($classDir -ne (Join-Path $PSScriptRoot 'bin')) { throw 'Unexpected class output path.' }
    if (Test-Path -LiteralPath $classDir) { Remove-Item -LiteralPath $classDir -Recurse -Force }
    New-Item -ItemType Directory -Force bin,build,dist | Out-Null
    $sources = @(Get-ChildItem src -Filter *.java -Recurse | ForEach-Object FullName)
    & $JavacExe --release 17 -encoding UTF-8 -Xlint:all -cp lib/Pokemon.jar -d bin $sources
    if ($LASTEXITCODE -ne 0) { throw 'Compilation failed.' }
    "Manifest-Version: 1.0`nMain-Class: Main`nClass-Path: ../lib/Pokemon.jar`n`n" | Set-Content build/manifest.mf -Encoding ascii
    & $JarExe '-J-Djava.io.tmpdir=build' --create --file dist/Lab2Pokemon.jar --manifest build/manifest.mf -C bin .
    if ($LASTEXITCODE -ne 0) { throw 'JAR creation failed.' }
    Write-Host 'Build OK: dist/Lab2Pokemon.jar'
} finally { Pop-Location }

