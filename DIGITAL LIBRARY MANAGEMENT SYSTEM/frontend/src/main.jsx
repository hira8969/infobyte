import React, { useEffect, useMemo, useState } from 'react';
import { createRoot } from 'react-dom/client';
import { BookOpen, Library, Lock, LogIn, Mail, Phone, Search, Shield, User, UserPlus } from 'lucide-react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './styles.css';
import { api, setToken } from './services/api';

function App() {
  const [auth, setAuth] = useState(() => JSON.parse(localStorage.getItem('auth') || 'null'));
  const [books, setBooks] = useState([]);
  const [stats, setStats] = useState(null);
  const [query, setQuery] = useState('');
  const [message, setMessage] = useState('');
  const [authMode, setAuthMode] = useState('login');
  const [authError, setAuthError] = useState('');
  const [isSubmittingAuth, setIsSubmittingAuth] = useState(false);
  const isAdmin = auth?.roles?.includes('ADMIN');

  useEffect(() => {
    if (auth?.token) setToken(auth.token);
  }, [auth]);

  useEffect(() => {
    loadBooks();
  }, []);

  useEffect(() => {
    if (isAdmin) {
      api.get('/admin/dashboard').then((res) => setStats(res.data)).catch(() => {});
    }
  }, [isAdmin]);

  const filteredBooks = useMemo(() => books.filter((book) => {
    const value = `${book.title} ${book.author} ${book.category} ${book.isbn}`.toLowerCase();
    return value.includes(query.toLowerCase());
  }), [books, query]);

  async function loadBooks() {
    const res = await api.get('/books?size=30&sort=title,asc');
    setBooks(res.data.content || []);
  }

  async function handleAuth(endpoint, form) {
    try {
      setIsSubmittingAuth(true);
      setAuthError('');
      const data = Object.fromEntries(new FormData(form));
      const res = await api.post(`/auth/${endpoint}`, data);
      localStorage.setItem('auth', JSON.stringify(res.data));
      setAuth(res.data);
      setMessage(`Welcome ${res.data.username}`);
    } catch (error) {
      const apiMessage = error.response?.data?.message;
      const fieldErrors = error.response?.data?.fields;
      setAuthError(apiMessage || Object.values(fieldErrors || {})[0] || 'Authentication failed. Please try again.');
    } finally {
      setIsSubmittingAuth(false);
    }
  }

  async function issueBook(bookId) {
    await api.post('/issues', { bookId });
    setMessage('Book issued successfully.');
    loadBooks();
  }

  async function reserveBook(bookId) {
    await api.post('/reservations', { bookId });
    setMessage('Reservation created.');
  }

  function logout() {
    localStorage.removeItem('auth');
    setToken(null);
    setAuth(null);
  }

  return (
    <div>
      <nav className="navbar navbar-expand-lg bg-white border-bottom sticky-top">
        <div className="container">
          <span className="navbar-brand d-flex align-items-center gap-2 fw-bold">
            <Library size={22} /> Digital Library
          </span>
          <div className="d-flex align-items-center gap-2">
            {auth ? (
              <>
                <span className="small text-secondary">{auth.username}</span>
                <button className="btn btn-outline-dark btn-sm" onClick={logout}>Logout</button>
              </>
            ) : (
              <a className="btn btn-dark btn-sm" href="#login"><LogIn size={16} /> Login</a>
            )}
          </div>
        </div>
      </nav>

      <main className="container py-4">
        <section className="hero mb-4">
          <div>
            <h1>Digital Library Management System</h1>
            <p>Search, issue, reserve, and manage books through a clean MVC + JPA system.</p>
          </div>
        </section>

        {message && <div className="alert alert-success">{message}</div>}

        {isAdmin && stats && (
          <section className="metric-grid mb-4">
            <Metric label="Total Books" value={stats.totalBooks} />
            <Metric label="Total Users" value={stats.totalUsers} />
            <Metric label="Issued Books" value={stats.issuedBooks} />
            <Metric label="Pending Fines" value={`Rs. ${stats.totalPendingFines}`} />
          </section>
        )}

        <section className="row g-4">
          <div className="col-lg-8">
            <div className="toolbar mb-3">
              <div className="input-group">
                <span className="input-group-text"><Search size={18} /></span>
                <input className="form-control" placeholder="Search by title, author, category, ISBN" value={query} onChange={(e) => setQuery(e.target.value)} />
              </div>
            </div>
            <div className="book-grid">
              {filteredBooks.map((book) => (
                <article className="book-card" key={book.id}>
                  <div>
                    <span className="badge text-bg-light">{book.category}</span>
                    <h2>{book.title}</h2>
                    <p>{book.author}</p>
                    <small>{book.isbn}</small>
                  </div>
                  <div className="d-flex align-items-center justify-content-between">
                    <strong>{book.availableQuantity}/{book.quantity} available</strong>
                    {auth && (
                      book.availableQuantity > 0
                        ? <button className="btn btn-primary btn-sm" onClick={() => issueBook(book.id)}>Issue</button>
                        : <button className="btn btn-outline-primary btn-sm" onClick={() => reserveBook(book.id)}>Reserve</button>
                    )}
                  </div>
                </article>
              ))}
            </div>
          </div>

          <aside className="col-lg-4">
            {!auth && (
              <AuthPanel
                authMode={authMode}
                setAuthMode={setAuthMode}
                authError={authError}
                isSubmitting={isSubmittingAuth}
                onSubmit={handleAuth}
              />
            )}
            {auth && (
              <div className="panel">
                <h2><Shield size={20} /> Dashboard</h2>
                <p className="text-secondary mb-0">Use the REST APIs for issued books, fines, reservations, reports, and admin operations. This UI is ready to expand into separate routed pages.</p>
              </div>
            )}
            <div className="panel mt-3">
              <h2><BookOpen size={20} /> Contact</h2>
              <ContactForm setMessage={setMessage} />
            </div>
          </aside>
        </section>
      </main>
    </div>
  );
}

function AuthPanel({ authMode, setAuthMode, authError, isSubmitting, onSubmit }) {
  const isLogin = authMode === 'login';

  function switchMode(mode) {
    setAuthMode(mode);
  }

  return (
    <div className="auth-panel" id="login">
      <div className="auth-head">
        <span className="auth-icon">{isLogin ? <LogIn size={22} /> : <UserPlus size={22} />}</span>
        <div>
          <h2>{isLogin ? 'Welcome Back' : 'Create Account'}</h2>
          <p>{isLogin ? 'Access your library dashboard.' : 'Start borrowing and reserving books.'}</p>
        </div>
      </div>

      <div className="auth-switch" role="tablist" aria-label="Authentication mode">
        <button
          type="button"
          className={isLogin ? 'active' : ''}
          onClick={() => switchMode('login')}
          aria-selected={isLogin}
        >
          Login
        </button>
        <button
          type="button"
          className={!isLogin ? 'active' : ''}
          onClick={() => switchMode('register')}
          aria-selected={!isLogin}
        >
          Register
        </button>
      </div>

      {authError && <div className="auth-error">{authError}</div>}

      <form className="auth-form" onSubmit={(e) => { e.preventDefault(); onSubmit(authMode, e.currentTarget); }}>
        {!isLogin && (
          <>
            <Field icon={<User size={17} />} label="Full name" name="fullName" autoComplete="name" required />
            <Field icon={<Mail size={17} />} label="Email" name="email" type="email" autoComplete="email" required />
          </>
        )}
        <Field icon={<User size={17} />} label="Username" name="username" autoComplete="username" required />
        {!isLogin && <Field icon={<Phone size={17} />} label="Phone number" name="phoneNumber" autoComplete="tel" />}
        <Field
          icon={<Lock size={17} />}
          label="Password"
          name="password"
          type="password"
          autoComplete={isLogin ? 'current-password' : 'new-password'}
          minLength={isLogin ? undefined : 8}
          required
        />
        {!isLogin && <p className="password-note">Use at least 8 characters with uppercase, lowercase, and a number.</p>}
        <button className="btn btn-primary auth-submit" disabled={isSubmitting}>
          {isSubmitting ? 'Please wait...' : isLogin ? 'Login' : 'Create Account'}
        </button>
      </form>
    </div>
  );
}

function Field({ icon, label, name, type = 'text', ...props }) {
  return (
    <label className="auth-field">
      <span>{label}</span>
      <div>
        {icon}
        <input name={name} type={type} placeholder={label} {...props} />
      </div>
    </label>
  );
}

function Metric({ label, value }) {
  return (
    <div className="metric">
      <span>{label}</span>
      <strong>{value}</strong>
    </div>
  );
}

function ContactForm({ setMessage }) {
  async function submit(event) {
    event.preventDefault();
    const data = Object.fromEntries(new FormData(event.currentTarget));
    await api.post('/contact', data);
    event.currentTarget.reset();
    setMessage('Query submitted.');
  }

  return (
    <form onSubmit={submit}>
      <input className="form-control mb-2" name="name" placeholder="Name" required />
      <input className="form-control mb-2" name="email" placeholder="Email" type="email" required />
      <input className="form-control mb-2" name="subject" placeholder="Subject" required />
      <textarea className="form-control mb-3" name="message" placeholder="Message" rows="3" required />
      <button className="btn btn-primary w-100">Send</button>
    </form>
  );
}

createRoot(document.getElementById('root')).render(<App />);
