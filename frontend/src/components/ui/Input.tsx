import './Input.scss'

interface InputProps extends React.InputHTMLAttributes<HTMLInputElement | HTMLTextAreaElement> {
  label?: string
  error?: string
  multiline?: boolean
  rows?: number
}

const Input = ({ label, error, multiline, rows = 4, className = '', ...props }: InputProps) => {
  const inputClass = `input__field ${error ? 'input__field--error' : ''} ${className}`

  return (
    <div className="input">
      {label && <label className="input__label">{label}</label>}
      {multiline ? (
        <textarea className={inputClass} rows={rows} {...(props as React.TextareaHTMLAttributes<HTMLTextAreaElement>)} />
      ) : (
        <input className={inputClass} {...(props as React.InputHTMLAttributes<HTMLInputElement>)} />
      )}
      {error && <span className="input__error">{error}</span>}
    </div>
  )
}

export default Input
