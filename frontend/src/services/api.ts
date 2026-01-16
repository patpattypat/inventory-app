import axios, { AxiosError } from 'axios'
import { InventoryItem } from '@/types'

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || '/api'

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
})

// Response interceptor for error handling
api.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => {
    console.error('API Error:', error.response?.data || error.message)
    return Promise.reject(error)
  }
)

export const inventoryApi = {
  // Get all items
  getAllItems: async (): Promise<InventoryItem[]> => {
    const response = await api.get('/inventory')
    return response.data
  },

  // Get item by ID
  getItemById: async (id: number): Promise<InventoryItem> => {
    const response = await api.get(`/inventory/${id}`)
    return response.data
  },

  // Create new item
  createItem: async (item: InventoryItem): Promise<InventoryItem> => {
    const response = await api.post('/inventory', item)
    return response.data
  },

  // Update existing item
  updateItem: async (id: number, item: InventoryItem): Promise<InventoryItem> => {
    const response = await api.put(`/inventory/${id}`, item)
    return response.data
  },

  // Delete item
  deleteItem: async (id: number): Promise<void> => {
    await api.delete(`/inventory/${id}`)
  },

  // Search items
  searchItems: async (term: string): Promise<InventoryItem[]> => {
    const response = await api.get(`/inventory/search`, { params: { term } })
    return response.data
  },

  // Export to Excel
  exportToExcel: async (): Promise<Blob> => {
    const response = await api.get('/inventory/export/excel', {
      responseType: 'blob',
    })
    return response.data
  },

  // Get all categories
  getCategories: async (): Promise<string[]> => {
    const response = await api.get('/inventory/categories')
    return response.data
  },

  // Get all conditions
  getConditions: async (): Promise<string[]> => {
    const response = await api.get('/inventory/conditions')
    return response.data
  },
}

export const sampleDataApi = {
  // Load sample data
  loadSampleData: async (): Promise<{ success: boolean; message: string; hasData: boolean }> => {
    const response = await api.post('/sample-data/load')
    return response.data
  },

  // Get sample data status
  getStatus: async (): Promise<{ hasData: boolean; message: string }> => {
    const response = await api.get('/sample-data/status')
    return response.data
  },

  // Clear all data
  clearAllData: async (): Promise<{ success: boolean; message: string; hasData: boolean }> => {
    const response = await api.delete('/sample-data/clear')
    return response.data
  },
}

export default api
