#let project(title: "", authors: (), body) = {
  set document(author: authors, title: title)
  set page(numbering: "1", number-align: center)
  set text(font: "New Computer Modern", lang: "pl")
  set heading(numbering: "1.1")

  page(numbering: none)[
    #align(center)[
      #v(2cm)
      #text(size: 14pt, weight: "bold")[UNIWERSYTET GDAŃSKI]
      #v(1.5em)
      #text(size: 12pt, weight: "bold")[WYDZIAŁ MATEMATYKI, FIZYKI I INFORMATYKI]

      #v(4cm)

      #text(size: 12pt)[
        Bartłomiej Wnuk, Michał Witkowski, Wiktor Sieracki \
        285786, 278862, 285769
      ]
      #v(4em)

      Kierunek: Informatyka Praktyczna
      #v(2.5em)

      #text(size: 24pt, weight: "bold")[System informatyczny wspomagający
        wyszukiwanie i ocenę lokalizacji
        miejskich - Mapzilla]
      #v(2em)

      #text(size: 12pt)[Praca licencjacka]
      #v(1em)

      napisana pod kierunkiem
      #v(1.5em)

      dr. Adama Kostulaka, prof. UG
      #v(2em)


      #text(size: 12pt)[Gdańsk 2025]
    ]
  ]

  body
}

#show: project.with(
  title: "Mapzilla",
  authors: (
    "Bartłomiej Wnuk",
    "Michał Witkowski",
    "Wiktor Sieracki",
  ),
)

#outline(title: "Spis treści", indent: auto)
#pagebreak()

= Opis problemu

Patrząc na ciągle rozwijający się rynek nieruchomości, konsumenci mogą mieć problem z wyborem odpowiadającego im miejsca do zamieszkania. Brak dostępu do kluczowych zasobów w mieście powoduje u ludzi wybieranie mniej płatnych prac oraz pogłębia różnice społeczne. Dzieci, które mają gorszy dostęp do szkoły, często gorzej radzą sobie w nauce oraz mają mniej czasu na pogłębianie swoich zainteresowań.

== Czym jest miasto 15 minut?

Miasto 15 minut to koncepcja urbanistyczna, w której mieszkańcy mają dostęp do najważniejszych udogodnień w zasięgu 15 minut pieszo. Do udogodnień zalicza się szkoły, sklepy spożywcze, apteki, przychodnie oraz miejsca rekreacji. @pfr_miasto15

#figure(
  image("assets/miasto15minut.png", width: 75%),
  caption: "Koncepcja miasta 15-minutowego",
)

== Jak osiągnąć status miasta 15 minut?

Żeby dobrze rozplanować gdzie zbudować nowe miejsca codziennego użytku należy śledzić położenie zbudowanych już struktur. W aplikacji można łatwo zauważyć miejsca, w których jest gorszy dostęp do sklepów. Czy w pobliżu znajduje się szkoła.

Dzięki aplikacji władze miasta mogą monitorować takie krytyczne miejsca i na jej podstawie rozplanowywać inwestycje na przyszłe lata. Należy zauważyć, że sprawna komunikacja miejska nie jest sposobem na zostanie miastem 15 minut. Trzeba jednak pójść o krok dalej i skupić się na inteligentnym rozmieszczeniu każdej budowli. Warto też trzymać się zasad takich jak budowanie większej ilości przestrzeni dla ludzi niż dla samochodów.

== Miasta, które są miastami 15 minut

Barcelona - jest prekursorem jeżeli chodzi o rozwiązania miasta 15 minut. Jako miasto liczące ponad półtora miliona mieszkańców, osiągnęło miano miasta 15 minut dzięki swojej wielkiej gęstości zaludnienia oraz specyficznej zabudowie bloków widocznej na Rysunku 2. Na terenie miasta i okolic (Sant Adrià de Besòs, L’Hospitalet de Llobregat, Esplugues de Llobregat y Cornellà de Llobregat) obowiązuje tzw. Strefa Niskiej Emisji (Zona de Bajas Emisiones – ZBE). Oznacza to ograniczenie wjazdu na teren ZBE dla pojazdów osobowych niespełniających wymogów dot. emisji spalin od poniedziałku do piątku w godzinach od 7 do 20. @gov_barcelona

#figure(
  image("assets/barcelona.png", width: 100%),
  caption: [Barcelona - przykład miasta 15-minutowego @wiki_barcelona],
)

- Melbourne - wspiera lokalne firmy
- Portland - buduje mieszkania komunalne w bogatych dzielnicach
- Paryż - dba o rozbudowę zielonych terenów przyszkolnych

== Zalety miasta 15 minut

- mniejsze zanieczyszczenie miasta spalinami
- poprawa jakości życia mieszkańców
- oszczędność czasu przeznaczanego na dojazdy
- zwiększenie integracji społecznej
- większa aktywność fizyczna mieszkańców

= Porównanie dostępnych rozwiązań

*Mapy Google* — pozwalają zobaczyć znajdujące się dookoła udogodnienia, lecz nie skupiają się na ich kompleksowej analizie i nie przedstawiają ich w dogodny sposób dla użytkownika planującego wybór miejsca zamieszkania.

*15-min city* — (https://app.developer.here.com/15-min-city-map/) mała aplikacja działająca tylko na terenie stanów zjednoczonych. Brak możliwości wyboru pożądanych miejsc.

= Możliwości zastosowania praktycznego

- *Dla mieszkańców* — pomoc w wyborze odpowiedniego miejsca do zamieszkania
- *Dla deweloperów* — planowanie inwestycji mieszkaniowych w najlepszych lokalizacjach
- *Dla władz miasta* — rozwój infrastruktury społecznej oraz planowanie przestrzenne zgodne z potrzebami mieszkańców.

= Struktura komunikacji pomiędzy poszczególnymi serwisami.
#figure(
  image("assets/struktura.png", width: 100%),
  caption: "Architektura aplikacji",
)

= Diagram autentykacji użytkownika.
#figure(
  image("assets/autentykacja.png", width: 100%),
  caption: "Diagram autoryzacji użytkownika",
)

= Przypadki użycia aplikacji.
#figure(
  image("assets/przypadki_uzycia.png", width: 100%),
  caption: "Przypadki użycia aplikacji",
)

// TODO: zmienić zdjęcie na bardziej czytelne
= Diagram Klas.
#figure(
  image("assets/diagram_klas.png", width: 100%),
  caption: "Diagram klas aplikacji",
)

= Wymagania

== Wymagania funkcjonalne
- Mapa w Aplikacji znajduje obiekty w całej Polsce.
- Dostępne jest wiele atrakcji do wyboru z listy.
- Możliwa jest rejestracja użytkownika.
- Możliwe jest logowanie użytkownika.

== Wymagania niefunkcjonalne
- Mapa działa płynnie.
- Użytkownik zostaje wylogowany po 5 minutach nieaktywności.
- Dane użytkowników są bezpiecznie przechowywane.

= Użyte technologie

== Backend
- *Spring Boot* — framework do budowy aplikacji webowych w języku Java

== Frontend
*React* — biblioteka do budowy interfejsów użytkownika.
*Next.js* — framework do budowy aplikacji webowych w React.
*Tailwind CSS* — framework CSS do budowy responsywnych interfejsów użytkownika. Pozwala na ujednolicenie stylów w całej aplikacji poprzez użycie zmiennych globalnych.

*Next-Auth* - biblioteka do autoryzacji użytkowników w Next.js. Pozwala na odczytanie tokenów JWT i pozyskanie danych użytkownika z Keycloak.

*TanstackQuery* - biblioteka do zarządzania stanem aplikacji. Pozwala na pobieranie danych z backendu. Dzięki niej można łatwo tworzyć ładowania oraz zarządzać błędami.

*Shadcn* - biblioteka z komponentami UI. Pozwala na użycie gotowych komponentów w aplikacji, takich jak przyciski, formularze, inputy itp.

*Keycloak* - system do autoryzacji i zarządzania użytkownikami. Pozwala na rejestrację użytkowników i autentykację ich za pomocą e-maila. Daje możliwość ustalania ról użytkowników np. roli admina lub użytkownika.

*PostgreSQL* - baza danych, w której przechowywane są dane o lokalizacjach, użytkownikach oraz relacjach między nimi.

*Docker* - narzędzie do konteneryzacji aplikacji. Pomaga w środowisku developerskim kontrolować wersje aplikacji oraz jej zależności.

*OpenStreetMap* — otwarta mapa świata, która pozwala na dodawanie i edytowanie danych geograficznych. Dzięki jej możemy znaleźć konkretne miejsce na mapie wpisując nazwę ulicy lub miasta.

= Użytkowanie aplikacji

== rejestracja
Aby zarejestrować się w aplikacji, należy podać swoje dane osobowe oraz adres e-mail. Odbywa się to poprzez keycloak, który jest zintegrowany z aplikacją.

#figure(
  image("assets/rejestracja.png", width: 100%),
  caption: "Rejestracja w aplikacji",
)

== logowanie
Aby zalogować się do aplikacji, należy podać swoją nazwę użytkownika oraz hasło. Po zalogowaniu użytkownik zostaje przekierowany do strony głównej aplikacji.

#figure(
  image("assets/logowanie.png", width: 100%),
  caption: "Logowanie do aplikacji",
)

== mapa
Aplikacja pozwala na przeglądanie mapy oraz dodawanie nowych lokalizacji. Użytkownik może wybrać interesujące go miejsca i wyszukać ich w okolicy.

#figure(
  image("assets/zbieranie_miejsc.png", width: 100%),
  caption: "Mapa z lokalizacjami",
)

== wyszukiwanie miejsca
Dzięki wyszukiwarce użytkownik może znaleźć interesujące go miejsca. Wystarczy wpisać nazwę miejsca miasta lub ulicy, a aplikacja przeniesie nas do wybranego miejsca.

#figure(
  image("assets/wyszukiwanie_miejsca.png", width: 100%),
  caption: "Wyszukiwanie miejsca",
)


== ulubione miejsca
Po kliknięciu w nazwę użytkownika w prawym górnym rogu, możemy przejść do zakładki ulubione miejsca. Można tam zauważyć listę ulubionych miejsc, które użytkownik dodał do swojego konta. Każde miejsce ma opisany wynik, koordynaty oraz dostępne i niedostępne tam udogodnienia.

#figure(
  image("assets/ulubione.png", width: 100%),
  caption: "Ulubione miejsca",
)

W tym miejscu możemy także porównywać ulubione miejsca.

#figure(
  image("assets/porownywanie.png", width: 100%),
  caption: "Porównanie ulubionych miejsc",
)


= Testowanie Aplikacji

#table(
  columns: (auto, auto),
  inset: 10pt,
  align: (left, center),
  [*Nazwa Testu*], [*Status*],
  [createLocation_ReturnCreated], [Done],
  [createLocation_ReturnCreated_CheckResponse], [Done],
  [createLocation_ReturnBadRequest_Score], [Done],
  // ... remaining test cases
)

#bibliography("references.bib")
