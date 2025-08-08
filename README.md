# 🗺️ MapZilla

**MapZilla** is a comprehensive location analysis platform that helps users find the perfect place to live by analyzing nearby amenities, services, and infrastructure. Whether you're looking for a new home, planning a move, or evaluating neighborhoods, MapZilla provides detailed insights about what's available in any area.

## 🌟 Features

### 🏠 **15-Minute City Analysis**

- Discover what amenities are within walking distance (15-minute radius) of any location
- Get a comprehensive score based on available services and facilities
- Visual map representation of nearby points of interest

### 🎯 **Smart Location Search**

- Interactive map interface powered by OpenStreetMap and Leaflet
- Click anywhere on the map to analyze that location
- Search for specific addresses, cities, or landmarks
- Real-time data from Overpass API for accurate, up-to-date information

### 📊 **Comprehensive Amenity Analysis**

Analyze availability of various place types including:

- **Healthcare**: hospitals, pharmacies, clinics
- **Education**: schools, universities, libraries
- **Shopping**: supermarkets, shops, malls
- **Transportation**: bus stops, train stations, parking
- **Recreation**: parks, gyms, cinemas, restaurants
- **Services**: banks, post offices, government offices

### ❤️ **Favorites & Comparison**

- Save your favorite locations for future reference
- Compare multiple locations side-by-side
- Label and categorize your saved places
- Track your location analysis history

### 🔐 **User Authentication**

- Secure authentication powered by Keycloak
- Personal dashboard with saved preferences
- User-specific favorites and history

## 🛠️ Technology Stack

### Frontend

- **Framework**: Next.js 15 with React 19
- **Styling**: Tailwind CSS with custom components
- **Maps**: Leaflet with React-Leaflet
- **State Management**: TanStack Query for server state
- **Authentication**: NextAuth.js with Keycloak integration
- **UI Components**: Radix UI primitives with custom styling

### Backend

- **Framework**: Spring Boot 3.4
- **Language**: Java 21
- **Security**: Spring Security with OAuth2 Resource Server
- **Database**: PostgreSQL 16
- **API Documentation**: OpenAPI/Swagger
- **Data Source**: Overpass API for OpenStreetMap data

### Infrastructure

- **Containerization**: Docker & Docker Compose
- **Database**: PostgreSQL with persistent volumes
- **Authentication**: Keycloak identity provider
- **Development**: Hot reload and watch mode support

## 🚀 Quick Start

### Prerequisites

- Docker and Docker Compose
- Node.js 18+ (for local development)
- Java 21+ (for local development)

### 1. Clone the Repository

```bash
git clone https://github.com/WiktorSieracki/MapZilla.git
cd MapZilla
```

### 2. Start with Docker Compose

```bash
# Start all services (database, backend, frontend, keycloak)
docker-compose up --build

# Or start in detached mode
docker-compose up -d --build
```

### 3. Access the Application

- **Frontend**: <http://localhost:3000>
- **Backend API**: <http://localhost:8080>
- **API Documentation**: <http://localhost:8080/swagger-ui.html>
- **Keycloak Admin**: <http://localhost:8090> (admin/admin)

## 🏗️ Development Setup

### Frontend Development

```bash
cd frontend
npm install
npm run dev
```

### Backend Development

```bash
cd backend
./gradlew bootRun
```

### Database Setup

```bash
# Start only the database
docker-compose up database -d
```

## 📱 How to Use

1. **🗺️ Explore the Map**: Click anywhere on the interactive map to analyze that location
2. **🎯 Select Amenities**: Choose which types of places you're interested in (restaurants, schools, hospitals, etc.)
3. **📊 Get Your Score**: View the calculated score based on available amenities within walking distance
4. **💾 Save Favorites**: Save interesting locations to your favorites for future reference
5. **⚖️ Compare Locations**: Select multiple saved locations to compare their amenities side-by-side
6. **📈 Track History**: Review your previous searches in the history section

## 🏛️ Architecture

```text
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│                 │    │                 │    │                 │
│    Frontend     │◄──►│     Backend     │◄──►│   PostgreSQL    │
│   (Next.js)     │    │  (Spring Boot)  │    │    Database     │
│                 │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │
         │                       │
         ▼                       ▼
┌─────────────────┐    ┌─────────────────┐
│                 │    │                 │
│    Keycloak     │    │  Overpass API   │
│ (Authentication)│    │ (Map Data)      │
│                 │    │                 │
└─────────────────┘    └─────────────────┘
```

## 📚 API Documentation

The backend provides a RESTful API with comprehensive documentation available at `/swagger-ui.html` when running the application.

### Key Endpoints

- `POST /api/map/locate` - Analyze a location for nearby amenities
- `GET /api/favorites` - Get user's favorite locations
- `POST /api/favorites` - Save a new favorite location
- `GET /api/history` - Get user's search history

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is part of an academic thesis and is available for educational purposes.

## 🎓 Academic Context

This project was developed as part of a bachelor's thesis focusing on creating tools for urban planning and location analysis based on the "15-minute city" concept. The goal is to help people make informed decisions about where to live based on proximity to essential services and amenities.

## 🔗 Links

- **Repository**: <https://github.com/WiktorSieracki/MapZilla>
- **OpenStreetMap**: <https://www.openstreetmap.org/>
- **Overpass API**: <https://overpass-api.de/>

---

Made with ❤️ for better urban living decisions
