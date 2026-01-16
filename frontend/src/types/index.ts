export interface InventoryItem {
  id?: number
  name: string
  description?: string
  quantity: number
  category: ItemCategory
  estimatedValue?: number
  purchasePrice?: number
  purchaseDate?: string
  manufacturer?: string
  partNumber?: string
  serialNumber?: string
  model?: string
  yearOfManufacture?: string
  condition?: ItemCondition
  storageLocation?: string
  provenance?: string
  compositionMaterials?: string
  certificationNumber?: string
  hasCertificate?: boolean
  documentationNotes?: string
  createdAt?: string
  updatedAt?: string
  notes?: string
}

export enum ItemCategory {
  AIRCRAFT_ENGINE = 'AIRCRAFT_ENGINE',
  AIRCRAFT_PROPELLER = 'AIRCRAFT_PROPELLER',
  AIRCRAFT_INSTRUMENTS = 'AIRCRAFT_INSTRUMENTS',
  AIRCRAFT_AVIONICS = 'AIRCRAFT_AVIONICS',
  AIRCRAFT_STRUCTURAL = 'AIRCRAFT_STRUCTURAL',
  AIRCRAFT_LANDING_GEAR = 'AIRCRAFT_LANDING_GEAR',
  AIRCRAFT_CONTROL_SURFACES = 'AIRCRAFT_CONTROL_SURFACES',
  AIRCRAFT_FUEL_SYSTEM = 'AIRCRAFT_FUEL_SYSTEM',
  AIRCRAFT_ELECTRICAL = 'AIRCRAFT_ELECTRICAL',
  AIRCRAFT_INTERIOR = 'AIRCRAFT_INTERIOR',
  CAR_ENGINE = 'CAR_ENGINE',
  CAR_TRANSMISSION = 'CAR_TRANSMISSION',
  CAR_SUSPENSION = 'CAR_SUSPENSION',
  CAR_BRAKES = 'CAR_BRAKES',
  CAR_ELECTRICAL = 'CAR_ELECTRICAL',
  CAR_INTERIOR = 'CAR_INTERIOR',
  CAR_EXTERIOR = 'CAR_EXTERIOR',
  CAR_EXHAUST = 'CAR_EXHAUST',
  CAR_FUEL_SYSTEM = 'CAR_FUEL_SYSTEM',
  CAR_STEERING = 'CAR_STEERING',
  TOOLS = 'TOOLS',
  MANUALS = 'MANUALS',
  MEMORABILIA = 'MEMORABILIA',
  OTHER = 'OTHER',
}

export enum ItemCondition {
  MINT = 'MINT',
  EXCELLENT = 'EXCELLENT',
  GOOD = 'GOOD',
  FAIR = 'FAIR',
  POOR = 'POOR',
  FOR_RESTORATION = 'FOR_RESTORATION',
  PARTS_ONLY = 'PARTS_ONLY',
}

export const CategoryLabels: Record<ItemCategory, string> = {
  [ItemCategory.AIRCRAFT_ENGINE]: 'Aircraft Engine',
  [ItemCategory.AIRCRAFT_PROPELLER]: 'Aircraft Propeller',
  [ItemCategory.AIRCRAFT_INSTRUMENTS]: 'Aircraft Instruments',
  [ItemCategory.AIRCRAFT_AVIONICS]: 'Aircraft Avionics',
  [ItemCategory.AIRCRAFT_STRUCTURAL]: 'Aircraft Structural Parts',
  [ItemCategory.AIRCRAFT_LANDING_GEAR]: 'Aircraft Landing Gear',
  [ItemCategory.AIRCRAFT_CONTROL_SURFACES]: 'Aircraft Control Surfaces',
  [ItemCategory.AIRCRAFT_FUEL_SYSTEM]: 'Aircraft Fuel System',
  [ItemCategory.AIRCRAFT_ELECTRICAL]: 'Aircraft Electrical System',
  [ItemCategory.AIRCRAFT_INTERIOR]: 'Aircraft Interior Components',
  [ItemCategory.CAR_ENGINE]: 'Car Engine',
  [ItemCategory.CAR_TRANSMISSION]: 'Car Transmission',
  [ItemCategory.CAR_SUSPENSION]: 'Car Suspension',
  [ItemCategory.CAR_BRAKES]: 'Car Brakes',
  [ItemCategory.CAR_ELECTRICAL]: 'Car Electrical System',
  [ItemCategory.CAR_INTERIOR]: 'Car Interior',
  [ItemCategory.CAR_EXTERIOR]: 'Car Exterior/Body',
  [ItemCategory.CAR_EXHAUST]: 'Car Exhaust System',
  [ItemCategory.CAR_FUEL_SYSTEM]: 'Car Fuel System',
  [ItemCategory.CAR_STEERING]: 'Car Steering System',
  [ItemCategory.TOOLS]: 'Tools and Equipment',
  [ItemCategory.MANUALS]: 'Manuals and Documentation',
  [ItemCategory.MEMORABILIA]: 'Memorabilia and Collectibles',
  [ItemCategory.OTHER]: 'Other',
}

export const ConditionLabels: Record<ItemCondition, string> = {
  [ItemCondition.MINT]: 'Mint - New or like new condition',
  [ItemCondition.EXCELLENT]: 'Excellent - Minor wear, fully functional',
  [ItemCondition.GOOD]: 'Good - Some wear, fully functional',
  [ItemCondition.FAIR]: 'Fair - Significant wear, functional with issues',
  [ItemCondition.POOR]: 'Poor - Heavy wear, needs restoration',
  [ItemCondition.FOR_RESTORATION]: 'For Restoration - Not functional, restoration project',
  [ItemCondition.PARTS_ONLY]: 'Parts Only - Not functional, for parts harvest',
}
