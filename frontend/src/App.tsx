import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { ThemeProvider } from './contexts/ThemeContext'
import Layout from './components/layout/Layout'
import InventoryList from './pages/InventoryList'
import InventoryForm from './pages/InventoryForm'
import './App.scss'

function App() {
  return (
    <ThemeProvider>
      <Router>
        <Layout>
          <Routes>
            <Route path="/" element={<InventoryList />} />
            <Route path="/add" element={<InventoryForm />} />
            <Route path="/edit/:id" element={<InventoryForm />} />
          </Routes>
        </Layout>
      </Router>
    </ThemeProvider>
  )
}

export default App
