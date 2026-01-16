import { ReactNode } from 'react'
import { useNavigate } from 'react-router-dom'
import { useTheme } from '@/contexts/ThemeContext'
import Button from '../ui/Button'
import './Layout.scss'

interface LayoutProps {
  children: ReactNode
}

const Layout = ({ children }: LayoutProps) => {
  const { theme, toggleTheme } = useTheme()
  const navigate = useNavigate()

  return (
    <div className="layout">
      <header className="layout__header">
        <div className="layout__header-content">
          <div className="layout__brand" onClick={() => navigate('/')}>
            <h1 className="layout__title">Collector's Inventory</h1>
            <p className="layout__subtitle">Aviation & Automotive Parts Collection</p>
          </div>
          <div className="layout__actions">
            <Button variant="ghost" size="sm" onClick={toggleTheme}>
              {theme === 'light' ? '🌙 Dark' : '☀️ Light'}
            </Button>
          </div>
        </div>
      </header>
      <main className="layout__main">
        <div className="layout__container">{children}</div>
      </main>
      <footer className="layout__footer">
        <p>&copy; 2024 Collector's Inventory. Built with passion for aviation and automotive history.</p>
      </footer>
    </div>
  )
}

export default Layout
