import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { inventoryApi, sampleDataApi } from '@/services/api'
import { InventoryItem, CategoryLabels, ConditionLabels } from '@/types'
import Button from '@/components/ui/Button'
import Card from '@/components/ui/Card'
import Input from '@/components/ui/Input'
import './InventoryList.scss'

const InventoryList = () => {
  const navigate = useNavigate()
  const [items, setItems] = useState<InventoryItem[]>([])
  const [filteredItems, setFilteredItems] = useState<InventoryItem[]>([])
  const [loading, setLoading] = useState(true)
  const [searchTerm, setSearchTerm] = useState('')
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    loadItems()
  }, [])

  useEffect(() => {
    if (searchTerm) {
      const filtered = items.filter(
        (item) =>
          item.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
          item.manufacturer?.toLowerCase().includes(searchTerm.toLowerCase()) ||
          item.partNumber?.toLowerCase().includes(searchTerm.toLowerCase()) ||
          item.serialNumber?.toLowerCase().includes(searchTerm.toLowerCase())
      )
      setFilteredItems(filtered)
    } else {
      setFilteredItems(items)
    }
  }, [searchTerm, items])

  const loadItems = async () => {
    try {
      setLoading(true)
      const data = await inventoryApi.getAllItems()
      setItems(data)
      setFilteredItems(data)
      setError(null)
    } catch (err) {
      setError('Failed to load inventory items')
      console.error(err)
    } finally {
      setLoading(false)
    }
  }

  const handleDelete = async (id: number) => {
    if (!confirm('Are you sure you want to delete this item?')) return

    try {
      await inventoryApi.deleteItem(id)
      await loadItems()
    } catch (err) {
      alert('Failed to delete item')
      console.error(err)
    }
  }

  const handleExport = async () => {
    try {
      const blob = await inventoryApi.exportToExcel()
      const url = window.URL.createObjectURL(blob)
      const a = document.createElement('a')
      a.href = url
      a.download = `inventory_export_${new Date().toISOString().split('T')[0]}.xlsx`
      document.body.appendChild(a)
      a.click()
      document.body.removeChild(a)
      window.URL.revokeObjectURL(url)
    } catch (err) {
      alert('Failed to export inventory')
      console.error(err)
    }
  }

  const handleLoadSampleData = async () => {
    if (!confirm('This will add 12 sample items (aviation & car parts) to your collection. Continue?')) {
      return
    }

    try {
      setLoading(true)
      const result = await sampleDataApi.loadSampleData()
      if (result.success) {
        alert(result.message)
        await loadItems()
      } else {
        alert(result.message)
      }
    } catch (err: any) {
      const message = err.response?.data?.message || 'Failed to load sample data'
      alert(message)
      console.error(err)
    } finally {
      setLoading(false)
    }
  }

  if (loading) {
    return (
      <div className="inventory-list">
        <div className="inventory-list__loading">Loading inventory...</div>
      </div>
    )
  }

  if (error) {
    return (
      <div className="inventory-list">
        <div className="inventory-list__error">{error}</div>
        <Button onClick={loadItems}>Retry</Button>
      </div>
    )
  }

  return (
    <div className="inventory-list">
      <div className="inventory-list__header">
        <div className="inventory-list__header-text">
          <h2>Your Collection</h2>
          <p>{items.length} items in inventory</p>
        </div>
        <div className="inventory-list__header-actions">
          <Button variant="secondary" onClick={handleExport}>
            📥 Export to Excel
          </Button>
          <Button variant="primary" onClick={() => navigate('/add')}>
            ✨ Add New Item
          </Button>
        </div>
      </div>

      <div className="inventory-list__search">
        <Input
          type="search"
          placeholder="Search by name, manufacturer, part number, or serial number..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      {filteredItems.length === 0 ? (
        <div className="inventory-list__empty">
          <h3>No items found</h3>
          <p>
            {searchTerm ? 'Try a different search term' : 'Start building your collection by adding items'}
          </p>
          {!searchTerm && (
            <div className="inventory-list__empty-actions">
              <Button variant="primary" onClick={() => navigate('/add')}>
                Add Your First Item
              </Button>
              <p className="inventory-list__empty-divider">or</p>
              <Button variant="secondary" onClick={handleLoadSampleData} disabled={loading}>
                Load Sample Data (12 items)
              </Button>
            </div>
          )}
        </div>
      ) : (
        <div className="inventory-list__grid">
          {filteredItems.map((item) => (
            <Card key={item.id} className="inventory-item">
              <div className="inventory-item__header">
                <h3 className="inventory-item__name">{item.name}</h3>
                <span className={`inventory-item__condition inventory-item__condition--${item.condition?.toLowerCase()}`}>
                  {item.condition ? ConditionLabels[item.condition].split('-')[0].trim() : 'N/A'}
                </span>
              </div>

              <div className="inventory-item__details">
                <div className="inventory-item__detail">
                  <span className="label">Category:</span>
                  <span className="value">{item.category ? CategoryLabels[item.category] : 'N/A'}</span>
                </div>

                {item.manufacturer && (
                  <div className="inventory-item__detail">
                    <span className="label">Manufacturer:</span>
                    <span className="value">{item.manufacturer}</span>
                  </div>
                )}

                {item.partNumber && (
                  <div className="inventory-item__detail">
                    <span className="label">Part Number:</span>
                    <span className="value">{item.partNumber}</span>
                  </div>
                )}

                {item.serialNumber && (
                  <div className="inventory-item__detail">
                    <span className="label">Serial Number:</span>
                    <span className="value">{item.serialNumber}</span>
                  </div>
                )}

                <div className="inventory-item__detail">
                  <span className="label">Quantity:</span>
                  <span className="value">{item.quantity}</span>
                </div>

                {item.estimatedValue && (
                  <div className="inventory-item__detail">
                    <span className="label">Est. Value:</span>
                    <span className="value">${item.estimatedValue.toLocaleString()}</span>
                  </div>
                )}
              </div>

              <div className="inventory-item__actions">
                <Button variant="outline" size="sm" onClick={() => navigate(`/edit/${item.id}`)}>
                  Edit
                </Button>
                <Button variant="ghost" size="sm" onClick={() => handleDelete(item.id!)}>
                  Delete
                </Button>
              </div>
            </Card>
          ))}
        </div>
      )}
    </div>
  )
}

export default InventoryList
