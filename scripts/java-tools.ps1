$ErrorActionPreference = 'Stop'
$jdk = $null
if ($env:JAVA_HOME -and (Test-Path (Join-Path $env:JAVA_HOME 'bin\javac.exe'))) { $jdk = $env:JAVA_HOME }
if (-not $jdk) {
    $found = Get-Command javac.exe -ErrorAction SilentlyContinue
    if ($found) { $jdk = Split-Path (Split-Path $found.Source) }
}
if (-not $jdk) {
    $roots = @((Join-Path $env:ProgramFiles 'Eclipse Adoptium'), (Join-Path $env:ProgramFiles 'Java'), (Join-Path $env:USERPROFILE '.jdks'))
    foreach ($root in $roots) {
        if (Test-Path $root) {
            $found = Get-ChildItem $root -Filter javac.exe -Recurse -ErrorAction SilentlyContinue | Select-Object -First 1
            if ($found) { $jdk = Split-Path (Split-Path $found.FullName); break }
        }
    }
}
if (-not $jdk) { throw 'JDK 17+ required. Install a JDK or set JAVA_HOME to its folder.' }
$script:JavaExe = Join-Path $jdk 'bin\java.exe'
$script:JavacExe = Join-Path $jdk 'bin\javac.exe'
$script:JarExe = Join-Path $jdk 'bin\jar.exe'

