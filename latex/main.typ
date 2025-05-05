#let project(title: "", authors: (), body) = {
  set document(author: authors, title: title)
  set page(numbering: "1", number-align: center)
  set text(font: "New Computer Modern", lang: "pl")
  
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
      
      #text(size: 24pt, weight: "bold")[Mapzilla]
      #v(2em)
      
      #text(size: 12pt)[Praca licencjacka]
      #v(1em)
      
      napisana pod kierunkiem
      #v(1.5em)
      
      dr. Adama Kostulaka, prof. UG
      #v(2em)
      
      #v(4cm)
      
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
    "Wiktor Sieracki"
  )
)

#outline(title: "Spis treści", indent: auto)
#pagebreak()

= Opis problemu

Patrząc na ciągle rozwijający się rynek nieruchomości, konsumenci mogą mieć problem z wyborem odpowiadającego im miejsca do zamieszkania. Brak dostępu do kluczowych zasobów w mieście powoduje u ludzi wybieranie mniej płatnych prac oraz pogłębia różnice społeczne. Dzieci, które mają gorszy dostęp do szkoły, często gorzej radzą sobie w nauce oraz mają mniej czasu na pogłębianie swoich zainteresowań.

== Czym jest miasto 15 minut?

Miasto 15 minut to koncepcja urbanistyczna, w której mieszkańcy mają dostęp do najważniejszych udogodnień w zasięgu 15 minut pieszo. Do udogodnień zalicza się szkoły, sklepy spożywcze, apteki, przychodnie oraz miejsca rekreacji. @pfr_miasto15

#figure(
  image("assets/miasto15minut.png", width: 75%),
  caption: "Koncepcja miasta 15-minutowego"
)

== Jak osiągnąć status miasta 15 minut?

Żeby dobrze rozplanować gdzie zbudować nowe miejsca codziennego użytku należy śledzić położenie zbudowanych już struktur. W aplikacji można łatwo zauważyć miejsca, w których jest gorszy dostęp do sklepów. Czy w pobliżu znajduje się szkoła.

== Miasta, które są miastami 15 minut

- Barcelona - jest prekursorem jeżeli chodzi o rozwiązania miasta 15 minut. Jako miasto liczące ponad półtora miliona mieszkańców, osiągnęło miano miasta 15 minut dzięki swojej wielkiej gęstości zaludnienia oraz specyficznej zabudowie bloków.

#figure(
  image("assets/barcelona.png", width: 100%),
  caption: "Barcelona - przykład miasta 15-minutowego"
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
- *Dla władz miasta* — rozwój infrastruktury społecznej oraz planowanie przestrzenne

= Użyte technologie

== Backend
- *Spring Boot* — framework do budowy aplikacji webowych w języku Java

== Frontend
- *React* — biblioteka do budowy interfejsów użytkownika
- *Next.js* — framework do budowy aplikacji webowych w React
- *Tailwind CSS* — framework CSS do budowy responsywnych interfejsów użytkownika

== Pozostałe
- *OpenStreetMap* — otwarta mapa świata, która pozwala na dodawanie i edytowanie danych geograficznych

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