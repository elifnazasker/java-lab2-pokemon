# Pokémon Java Lab 2 — Varyant 83447690

**Fotoğraflardaki altı Pokémon ve saldırı listesi uygulandı.**
Önceki geçici saldırılar kaldırıldı. 21 Java kaynak dosyası, 12 saldırı sınıfı var.

## Çalıştırma

1. ZIP'i bir klasöre çıkar; dosyaları ZIP'in içinden çalıştırma.
2. `run.cmd` dosyasına çift tıkla. Programı derleyip üçe üç savaşı başlatır.
3. VS Code kullanacaksan bu klasörü **File → Open Folder** ile aç, önerilen **Extension Pack for Java** eklentisini kur, `src/Main.java` içinden **Run** seç.
4. JDK 17 veya üstü gerekir. Bu bilgisayardaki Adoptium JDK 21 ile test edildi.

PowerShell:

```powershell
.\run.ps1
.\test.ps1
```

Betik çalıştırma engellenirse `run.cmd` kullan.
Java PATH'te olmasa da betikler kurulu Adoptium/Java JDK'sını arar.
VS Code editöründe kırmızı çizgi kalırsa **Java: Clean Java Language Server Workspace** komutunu çalıştır.
Kütüphane yolu `.vscode/settings.json` içinde hazırdır. VS Code arayüzünden çalıştırma ayrıca sınanmadı.

## Ödevdeki liste

İstatistik sırası: HP / Attack / Defense / Sp.Atk / Sp.Def / Speed.

| Pokémon | Tür | Temel değerler | Atanmış saldırılar |
| --- | --- | --- | --- |
| Claydol | GROUND/PSYCHIC | 60/70/105/70/120/75 | Overheat, Rest, KarateChop, ConfuseRay |
| Steelix | STEEL/GROUND | 75/85/200/55/65/30 | Punishment, DreamEater, Flamethrower |
| Raikou | ELECTRIC | 90/85/75/115/100/115 | Punishment, DreamEater, Flamethrower, SmackDown |
| Wooper | WATER/GROUND | 55/45/45/25/25/15 | FakeOut, Swagger |
| Flareon | FIRE | 65/130/60/95/110/65 | FakeOut, Swagger, Withdraw |
| Quagsire | WATER/GROUND | 95/85/85/65/65/35 | FakeOut, Swagger, Withdraw, ViceGrip |

Saldırı atamaları gönderilen fotoğraflardan okundu. Pokémonların oyunlarda bu saldırıları öğrenip öğrenemediğine göre liste değiştirilmedi.
Quagsire, Wooper'dan kalıtım alır: FakeOut ve Swagger'ı koruyup Withdraw ve ViceGrip ekler.
Flareon ayrı bir evrim ailesindedir; Wooper'dan türetilmedi.
Alt sınıfı olmayan tüm geliştirilmiş sınıflar `final`; Wooper genişletilebilir.
Seviye 50 ve takım dağılımı gösterim için seçildi:
Claydol/Raikou/Wooper — Steelix/Flareon/Quagsire.

## Dosyalar

- `dist/Lab2Pokemon.jar`: çalıştırılabilir uygulama. `lib/Pokemon.jar` ile birlikte proje düzeninde tutulmalı.
- `docs/REPORT_RU.md`: varyant numarası eklenmiş Rusça rapor taslağı.
- `docs/class-diagram.md`: sınıf diyagramı.
- `docs/MOVES.md`: güç, isabet, etkiler ve kaynaklar.
- `docs/VERIFICATION.md`: testler ve mekanik sınırlamalar.
- `docs/evidence/`: güncel test ve savaş günlükleri.
- `docs/ASSIGNMENT.md`: fotoğraflardaki gereksinimlerin dökümü.

## Doğrulama ve sınırlar

90 entegrasyon kontrolü geçti; iki tam savaş kazanan mesajıyla tamamlandı. Yeni kaynaklar derleme uyarısı olmadan derlendi.
Fake Out, kütüphanede ilk tur sayacı olmadığı için ilk **denenen saldırı** ile sınırlandırıldı; şaşkınlık gibi nedenlerle ilk hareket atlanırsa tam oyun kuralından farklı olabilir.
Smack Down yere indirme durumunu kaydeder; kütüphane uçuş/yetenek durumunu desteklemez. Atanmış listede Ground saldırısı veya uçan Pokémon yoktur.
Punishment ve Fake Out, JAR'ın genel API'sinde bulunmayan verilere erişmek için küçük bir uyumluluk sınıfı kullanır; bu sınıf verilen JAR'a bağlıdır.
JAR değiştirilmedi. Negatif özellik kademesi hesabındaki hata devam eder; ayrıntılar doğrulama belgesinde.
Rapor taslağında öğrenci ve grup alanlarını doldur.

## Elle derleme

Java komutları PATH'teyse:

```powershell
New-Item -ItemType Directory -Force bin | Out-Null
$sources = Get-ChildItem src -Filter *.java -Recurse | ForEach-Object FullName
javac --release 17 -encoding UTF-8 -cp lib/Pokemon.jar -d bin $sources
java -cp "bin;lib/Pokemon.jar" Main
```

Windows'ta sınıf yolu ayıracı `;`, Linux/macOS'ta `:` kullanılır.
Savaş çıktısı ve kazanan rastgele değişir; kütüphane her saldırıda yaklaşık bir saniye bekler.

