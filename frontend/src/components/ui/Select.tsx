import './Select.scss'

interface SelectProps extends React.SelectHTMLAttributes<HTMLSelectElement> {
  label?: string
  error?: string
  options: { value: string; label: string }[]
}

const Select = ({ label, error, options, className = '', ...props }: SelectProps) => {
  return (
    <div className="select">
      {label && <label className="select__label">{label}</label>}
      <select className={`select__field ${error ? 'select__field--error' : ''} ${className}`} {...props}>
        {options.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
      {error && <span className="select__error">{error}</span>}
    </div>
  )
}

export default Select
