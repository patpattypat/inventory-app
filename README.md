# Collector's Inventory System

A professional inventory management system for aviation and automotive parts collectors. Built with Spring Boot, React, TypeScript, and PostgreSQL, following clean architecture principles.

## Features

### Current Features
- **Sample Data Loading**: Optional feature to load 12 realistic test items including:
  - Aviation parts (P&W R-2800 engine, Hamilton propeller, Sperry gyroscope, etc.)
  - Automotive parts (Chevy 327 V8, Porsche 915 transmission, Nardi steering wheel, etc.)
  - Historical memorabilia (B-17 manual, 1955 Le Mans program)
  - Accessible via UI button when database is empty

- **Comprehensive Item Tracking**: Detailed fields for aviation and car parts including:
  - Basic information (name, description, quantity, category)
  - Technical details (manufacturer, part number, serial number, model, year)
  - Financial information (purchase price, estimated value, purchase date)
  - Condition tracking (7 condition levels from Mint to Parts Only)
  - Storage location management
  - Provenance documentation
  - Composition/materials information
  - Certification tracking

- **Beautiful UI**: HeroUI-inspired design with:
  - Modern, minimalist aesthetic
  - Smooth animations and transitions
  - Gradient accents (pink/magenta and warm oranges)
  - Rounded corners and subtle shadows
  - Dark/Light mode support
  - Fully responsive design

- **Excel Export**: Professional Excel reports with formatted columns and proper styling

- **Search & Filter**: Powerful search across name, manufacturer, part number, and serial number

- **Clean Architecture**:
  - Backend: Domain, Application, and Infrastructure layers
  - Frontend: Separation of concerns with services, components, and pages
  - Type-safe API communication

### Future Features (Planned)
- User authentication and login
- Admin-only view and permissions
- Insurance extract generation
- Photo upload for items
- Advanced filtering and sorting
- Reports and analytics
- Maintenance history tracking

## Architecture

### Backend Architecture (Spring Boot)

```
backend/
├── domain/               # Domain Layer (Business Logic)
│   ├── model/           # Domain entities
│   └── repository/      # Repository interfaces
├── application/         # Application Layer (Use Cases)
│   ├── dto/            # Data Transfer Objects
│   ├── mapper/         # Entity-DTO mappers
│   └── service/        # Application services
└── infrastructure/      # Infrastructure Layer (External concerns)
    ├── config/         # Configuration
    ├── exception/      # Exception handling
    └── web/            # REST controllers
```

**Key Design Principles:**
- **Clean Architecture**: Clear separation of concerns
- **Domain-Driven Design**: Rich domain models with business logic
- **SOLID Principles**: Single responsibility, open-closed, dependency inversion
- **RESTful API**: Standard HTTP methods and status codes
- **Validation**: Bean Validation (JSR-303) for input validation
- **Error Handling**: Global exception handler with consistent error responses

### Frontend Architecture (React + TypeScript)

```
frontend/
├── src/
│   ├── components/      # Reusable UI components
│   │   ├── ui/         # Base UI components (Button, Card, Input, Select)
│   │   └── layout/     # Layout components (Header, Footer)
│   ├── pages/          # Page components
│   │   ├── InventoryList.tsx
│   │   └── InventoryForm.tsx
│   ├── services/       # API services
│   ├── contexts/       # React contexts (Theme)
│   ├── types/          # TypeScript type definitions
│   └── styles/         # Global styles and variables
```

**Key Design Principles:**
- **Component-Based Architecture**: Reusable, composable components
- **Type Safety**: Full TypeScript coverage
- **Clean Code**: Single Responsibility Principle for components
- **Separation of Concerns**: Services, contexts, and UI separated
- **Responsive Design**: Mobile-first approach
- **Accessibility**: Keyboard navigation and ARIA labels

### Database Schema

**InventoryItem Table:**
- Core fields: id, name, description, quantity, category
- Technical: manufacturer, partNumber, serialNumber, model, yearOfManufacture
- Financial: estimatedValue, purchasePrice, purchaseDate
- Condition: condition, storageLocation
- Provenance: provenance, compositionMaterials
- Certification: certificationNumber, hasCertificate, documentationNotes
- Metadata: createdAt, updatedAt, notes

**Indexes:** category, manufacturer, partNumber for optimized queries

## Tech Stack

### Backend
- **Framework**: Spring Boot 3.2.1
- **Language**: Java 17
- **Database**: PostgreSQL 16
- **ORM**: Spring Data JPA / Hibernate
- **Validation**: Bean Validation (Hibernate Validator)
- **Excel Export**: Apache POI 5.2.5
- **Build Tool**: Maven

### Frontend
- **Framework**: React 18
- **Language**: TypeScript
- **Build Tool**: Vite
- **Styling**: SCSS with CSS Variables
- **HTTP Client**: Axios
- **Routing**: React Router 6

### DevOps
- **Containerization**: Docker & Docker Compose
- **Database**: PostgreSQL in container
- **Web Server**: Nginx (for frontend)

## Prerequisites

- Docker and Docker Compose
- (Optional) Java 17+ for local development
- (Optional) Node.js 20+ for local development

## Quick Start with Docker Compose

1. **Clone the repository:**
   ```bash
   cd /Users/pat/work/inventory-app
   ```

2. **Start all services:**
   ```bash
   docker-compose up -d
   ```

   This will start:
   - PostgreSQL database on port 5432
   - Spring Boot backend on port 8080
   - React frontend on port 5173

3. **Access the application:**
   - Frontend: http://localhost:5173
   - Backend API: http://localhost:8080/api

4. **Stop all services:**
   ```bash
   docker-compose down
   ```

5. **Stop and remove all data:**
   ```bash
   docker-compose down -v
   ```

## Local Development Setup

### Backend Development

1. **Start PostgreSQL:**
   ```bash
   docker-compose up -d postgres
   ```

2. **Run Spring Boot:**
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

   Or with your IDE, run `InventoryApplication.java`

3. **API will be available at:** http://localhost:8080/api

### Frontend Development

1. **Install dependencies:**
   ```bash
   cd frontend
   npm install
   ```

2. **Run development server:**
   ```bash
   npm run dev
   ```

3. **Application will be available at:** http://localhost:5173

### Loading Sample Data

When you first access the application with an empty database, you'll see an option to load sample data:

1. **Via UI**: Click the "Load Sample Data (12 items)" button on the empty inventory page
2. **Via API**:
   ```bash
   curl -X POST http://localhost:8080/api/sample-data/load
   ```

The sample data includes:
- 5 aviation parts (engines, propeller, instruments, avionics, landing gear)
- 5 automotive parts (engines, transmission, carburetor, steering wheel, exhaust)
- 2 memorabilia items (B-17 manual, Le Mans race program)

All items have realistic details including provenance, condition notes, and technical specifications.

**Note**: Sample data can only be loaded into an empty database. If you want to reload it, first clear all data using the API:
```bash
curl -X DELETE http://localhost:8080/api/sample-data/clear
```

### Build for Production

**Backend:**
```bash
cd backend
./mvnw clean package
```

**Frontend:**
```bash
cd frontend
npm run build
```

## API Endpoints

### Inventory Items

- `GET /api/inventory` - Get all items
- `GET /api/inventory/{id}` - Get item by ID
- `POST /api/inventory` - Create new item
- `PUT /api/inventory/{id}` - Update item
- `DELETE /api/inventory/{id}` - Delete item
- `GET /api/inventory/search?term={term}` - Search items
- `GET /api/inventory/category/{category}` - Get items by category
- `GET /api/inventory/condition/{condition}` - Get items by condition
- `GET /api/inventory/low-stock` - Get low stock items (quantity ≤ 1)
- `GET /api/inventory/export/excel` - Export all items to Excel

### Reference Data

- `GET /api/inventory/categories` - Get all available categories
- `GET /api/inventory/conditions` - Get all available conditions

### Sample Data

- `POST /api/sample-data/load` - Load 12 sample items (only works if database is empty)
- `GET /api/sample-data/status` - Check if database has data
- `DELETE /api/sample-data/clear` - Delete all data (use with caution!)

## Environment Variables

### Backend (application.yml)

```yaml
DB_HOST: localhost          # Database host
DB_PORT: 5432              # Database port
DB_NAME: inventory         # Database name
DB_USERNAME: inventory_user # Database username
DB_PASSWORD: inventory_pass # Database password
CORS_ALLOWED_ORIGINS: http://localhost:5173  # Allowed CORS origins
```

### Frontend (.env)

```
VITE_API_BASE_URL=http://localhost:8080/api
```

## Categories

The system supports the following categories:

**Aviation Parts:**
- Aircraft Engine
- Aircraft Propeller
- Aircraft Instruments
- Aircraft Avionics
- Aircraft Structural Parts
- Aircraft Landing Gear
- Aircraft Control Surfaces
- Aircraft Fuel System
- Aircraft Electrical System
- Aircraft Interior Components

**Automotive Parts:**
- Car Engine
- Car Transmission
- Car Suspension
- Car Brakes
- Car Electrical System
- Car Interior
- Car Exterior/Body
- Car Exhaust System
- Car Fuel System
- Car Steering System

**General:**
- Tools and Equipment
- Manuals and Documentation
- Memorabilia and Collectibles
- Other

## Condition Levels

1. **Mint** - New or like new condition
2. **Excellent** - Minor wear, fully functional
3. **Good** - Some wear, fully functional
4. **Fair** - Significant wear, functional with issues
5. **Poor** - Heavy wear, needs restoration
6. **For Restoration** - Not functional, restoration project
7. **Parts Only** - Not functional, for parts harvest

## Design System (HeroUI-Inspired)

### Color Palette

**Light Mode:**
- Primary: `#F54C7A` (Pink)
- Primary Hover: `#FF72E1` (Magenta)
- Secondary: `#FF705B` (Orange)
- Accent: `#FFB457` (Warm Orange)

**Dark Mode:**
- Background: `#0a0a0a`
- Surface: `#1a1a1a`
- Text: `#ffffff`

### Typography
- Font: System font stack (San Francisco, Segoe UI, Roboto, etc.)
- Weights: 400 (normal), 500 (medium), 600 (semibold), 700 (bold)
- Scale: xs (0.75rem) to 6xl (3.75rem)

### Components
- **Border Radius**: 0.375rem to 1.5rem (rounded corners)
- **Shadows**: Subtle layering with multiple shadow levels
- **Transitions**: 150ms to 300ms cubic-bezier for smooth animations
- **Focus States**: 2px outline with primary color

## Project Structure Overview

```
inventory-app/
├── backend/                 # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/collector/inventory/
│   │   │   │   ├── domain/
│   │   │   │   ├── application/
│   │   │   │   └── infrastructure/
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   ├── Dockerfile
│   └── pom.xml
├── frontend/               # React frontend
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── contexts/
│   │   ├── types/
│   │   └── styles/
│   ├── Dockerfile
│   ├── nginx.conf
│   └── package.json
├── docker-compose.yml
└── README.md
```

## Contributing

This is a personal collector's inventory system. Feel free to fork and adapt for your own use.

## Future Enhancements

### Phase 2 - Authentication & Authorization
- User registration and login
- JWT-based authentication
- Role-based access control (Admin, Viewer)
- User profile management

### Phase 3 - Advanced Features
- Photo upload and gallery
- Insurance extract PDF generation
- Advanced search with filters
- Sorting by multiple fields
- Bulk operations (import/export CSV)
- Audit log/history tracking

### Phase 4 - Analytics & Insights
- Collection value dashboard
- Category breakdown charts
- Condition distribution
- Purchase trends over time
- Low stock alerts

### Phase 5 - Collaboration
- Share collections with other users
- Public collection pages
- Comments and notes on items
- Item comparison feature

## License

MIT License - feel free to use this for your own collections!

## Support

For issues or questions, please consult the documentation or examine the source code.

---
## Deplyoment
Will at push on master automatically deploy

**Built with passion for aviation and automotive history** ✈️ 🚗
