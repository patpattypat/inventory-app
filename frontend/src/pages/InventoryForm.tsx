import { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { inventoryApi } from '@/services/api'
import { InventoryItem, ItemCategory, ItemCondition, CategoryLabels, ConditionLabels } from '@/types'
import Button from '@/components/ui/Button'
import Input from '@/components/ui/Input'
import Select from '@/components/ui/Select'
import Card from '@/components/ui/Card'
import './InventoryForm.scss'

const InventoryForm = () => {
  const navigate = useNavigate()
  const { id } = useParams()
  const isEdit = !!id

  const [loading, setLoading] = useState(false)
  const [formData, setFormData] = useState<InventoryItem>({
    name: '',
    description: '',
    quantity: 1,
    category: ItemCategory.OTHER,
    estimatedValue: undefined,
    purchasePrice: undefined,
    purchaseDate: '',
    manufacturer: '',
    partNumber: '',
    serialNumber: '',
    model: '',
    yearOfManufacture: '',
    condition: undefined,
    storageLocation: '',
    provenance: '',
    compositionMaterials: '',
    certificationNumber: '',
    hasCertificate: false,
    documentationNotes: '',
    notes: '',
  })

  useEffect(() => {
    if (isEdit) {
      loadItem()
    }
  }, [id])

  const loadItem = async () => {
    try {
      setLoading(true)
      const data = await inventoryApi.getItemById(Number(id))
      setFormData(data)
    } catch (err) {
      alert('Failed to load item')
      console.error(err)
      navigate('/')
    } finally {
      setLoading(false)
    }
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)

    try {
      if (isEdit) {
        await inventoryApi.updateItem(Number(id), formData)
      } else {
        await inventoryApi.createItem(formData)
      }
      navigate('/')
    } catch (err) {
      alert(`Failed to ${isEdit ? 'update' : 'create'} item`)
      console.error(err)
    } finally {
      setLoading(false)
    }
  }

  const handleChange = (field: keyof InventoryItem, value: any) => {
    setFormData((prev) => ({ ...prev, [field]: value }))
  }

  const categoryOptions = Object.entries(CategoryLabels).map(([key, label]) => ({
    value: key,
    label: label,
  }))

  const conditionOptions = [
    { value: '', label: 'Select condition...' },
    ...Object.entries(ConditionLabels).map(([key, label]) => ({
      value: key,
      label: label,
    })),
  ]

  if (loading && isEdit) {
    return <div className="inventory-form__loading">Loading...</div>
  }

  return (
    <div className="inventory-form">
      <div className="inventory-form__header">
        <h2>{isEdit ? 'Edit Item' : 'Add New Item'}</h2>
        <Button variant="ghost" onClick={() => navigate('/')}>
          ← Back to List
        </Button>
      </div>

      <form onSubmit={handleSubmit}>
        <Card className="inventory-form__section">
          <h3>Basic Information</h3>
          <div className="inventory-form__grid">
            <Input
              label="Name *"
              value={formData.name}
              onChange={(e) => handleChange('name', e.target.value)}
              required
              placeholder="e.g., Pratt & Whitney R-2800 Double Wasp"
            />
            <Input
              label="Quantity *"
              type="number"
              min="0"
              value={formData.quantity}
              onChange={(e) => handleChange('quantity', parseInt(e.target.value) || 0)}
              required
            />
            <Select
              label="Category *"
              value={formData.category}
              onChange={(e) => handleChange('category', e.target.value as ItemCategory)}
              options={categoryOptions}
              required
            />
            <Select
              label="Condition"
              value={formData.condition || ''}
              onChange={(e) => handleChange('condition', e.target.value ? (e.target.value as ItemCondition) : undefined)}
              options={conditionOptions}
            />
          </div>
          <Input
            label="Description"
            value={formData.description || ''}
            onChange={(e) => handleChange('description', e.target.value)}
            multiline
            rows={3}
            placeholder="Detailed description of the item..."
          />
        </Card>

        <Card className="inventory-form__section">
          <h3>Technical Details</h3>
          <div className="inventory-form__grid">
            <Input
              label="Manufacturer"
              value={formData.manufacturer || ''}
              onChange={(e) => handleChange('manufacturer', e.target.value)}
              placeholder="e.g., Pratt & Whitney"
            />
            <Input
              label="Part Number"
              value={formData.partNumber || ''}
              onChange={(e) => handleChange('partNumber', e.target.value)}
              placeholder="e.g., P/N 12345-67"
            />
            <Input
              label="Serial Number"
              value={formData.serialNumber || ''}
              onChange={(e) => handleChange('serialNumber', e.target.value)}
              placeholder="e.g., S/N 98765"
            />
            <Input
              label="Model"
              value={formData.model || ''}
              onChange={(e) => handleChange('model', e.target.value)}
              placeholder="e.g., R-2800-59"
            />
            <Input
              label="Year of Manufacture"
              value={formData.yearOfManufacture || ''}
              onChange={(e) => handleChange('yearOfManufacture', e.target.value)}
              placeholder="e.g., 1943"
            />
            <Input
              label="Storage Location"
              value={formData.storageLocation || ''}
              onChange={(e) => handleChange('storageLocation', e.target.value)}
              placeholder="e.g., Hangar A, Shelf 3"
            />
          </div>
          <Input
            label="Composition / Materials"
            value={formData.compositionMaterials || ''}
            onChange={(e) => handleChange('compositionMaterials', e.target.value)}
            multiline
            rows={2}
            placeholder="Materials used, construction details..."
          />
        </Card>

        <Card className="inventory-form__section">
          <h3>Financial Information</h3>
          <div className="inventory-form__grid">
            <Input
              label="Purchase Price"
              type="number"
              step="0.01"
              min="0"
              value={formData.purchasePrice || ''}
              onChange={(e) => handleChange('purchasePrice', e.target.value ? parseFloat(e.target.value) : undefined)}
              placeholder="0.00"
            />
            <Input
              label="Estimated Value"
              type="number"
              step="0.01"
              min="0"
              value={formData.estimatedValue || ''}
              onChange={(e) => handleChange('estimatedValue', e.target.value ? parseFloat(e.target.value) : undefined)}
              placeholder="0.00"
            />
            <Input
              label="Purchase Date"
              type="date"
              value={formData.purchaseDate || ''}
              onChange={(e) => handleChange('purchaseDate', e.target.value)}
            />
          </div>
        </Card>

        <Card className="inventory-form__section">
          <h3>Provenance</h3>
          <Input
            label="Provenance"
            value={formData.provenance || ''}
            onChange={(e) => handleChange('provenance', e.target.value)}
            multiline
            rows={3}
            placeholder="Ownership history, chain of custody, origin..."
          />
        </Card>

        <Card className="inventory-form__section">
          <h3>Certification & Documentation</h3>
          <div className="inventory-form__grid">
            <Input
              label="Certification Number"
              value={formData.certificationNumber || ''}
              onChange={(e) => handleChange('certificationNumber', e.target.value)}
              placeholder="e.g., FAA 8130-3, ARC"
            />
            <div className="inventory-form__checkbox">
              <label>
                <input
                  type="checkbox"
                  checked={formData.hasCertificate || false}
                  onChange={(e) => handleChange('hasCertificate', e.target.checked)}
                />
                <span>Has Certificate</span>
              </label>
            </div>
          </div>
          <Input
            label="Documentation Notes"
            value={formData.documentationNotes || ''}
            onChange={(e) => handleChange('documentationNotes', e.target.value)}
            multiline
            rows={2}
            placeholder="Notes about available documentation, manuals, certificates..."
          />
        </Card>

        <Card className="inventory-form__section">
          <h3>Additional Notes</h3>
          <Input
            label="Notes"
            value={formData.notes || ''}
            onChange={(e) => handleChange('notes', e.target.value)}
            multiline
            rows={4}
            placeholder="Any additional information, maintenance notes, restoration plans..."
          />
        </Card>

        <div className="inventory-form__actions">
          <Button type="button" variant="ghost" onClick={() => navigate('/')}>
            Cancel
          </Button>
          <Button type="submit" variant="primary" disabled={loading}>
            {loading ? 'Saving...' : isEdit ? 'Update Item' : 'Create Item'}
          </Button>
        </div>
      </form>
    </div>
  )
}

export default InventoryForm
