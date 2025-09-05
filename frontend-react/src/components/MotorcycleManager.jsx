import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';
import config from './config.js';

const MotorcycleManager = () => {
  const [motorcycles, setMotorcycles] = useState([]);
  const [motorcycle, setMotorcycle] = useState({
    id: '',
    make: '',
    model: '',
    year: '',
    engineType: '',
    color: '',
    price: '',
    mileage: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedMotorcycle, setFetchedMotorcycle] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  const baseUrl = `${config.url}/motorcycleapi`;

  useEffect(() => {
    fetchAllMotorcycles();
  }, []);

  const fetchAllMotorcycles = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setMotorcycles(res.data);
    } catch (error) {
      setMessage('Failed to fetch motorcycles.');
    }
  };

  const handleChange = (e) => {
    setMotorcycle({ ...motorcycle, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    for (let key in motorcycle) {
      if (!motorcycle[key] || motorcycle[key].toString().trim() === '') {
        setMessage(`Please fill out the ${key} field.`);
        return false;
      }
    }
    return true;
  };

  const addMotorcycle = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, motorcycle);
      setMessage('Motorcycle added successfully.');
      fetchAllMotorcycles();
      resetForm();
    } catch (error) {
      setMessage('Error adding motorcycle.');
    }
  };

  const updateMotorcycle = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, motorcycle);
      setMessage('Motorcycle updated successfully.');
      fetchAllMotorcycles();
      resetForm();
    } catch (error) {
      setMessage('Error updating motorcycle.');
    }
  };

  const deleteMotorcycle = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllMotorcycles();
    } catch (error) {
      setMessage('Error deleting motorcycle.');
    }
  };

  const getMotorcycleById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedMotorcycle(res.data);
      setMessage('');
    } catch (error) {
      setFetchedMotorcycle(null);
      setMessage('Motorcycle not found.');
    }
  };

  const handleEdit = (bike) => {
    setMotorcycle(bike);
    setEditMode(true);
    setMessage(`Editing motorcycle with ID ${bike.id}`);
  };

  const resetForm = () => {
    setMotorcycle({
      id: '',
      make: '',
      model: '',
      year: '',
      engineType: '',
      color: '',
      price: '',
      mileage: ''
    });
    setEditMode(false);
  };

  return (
    <div className="student-container">

      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}

      <h2>Motorcycle Management</h2>

      <div>
        <h3>{editMode ? 'Edit Motorcycle' : 'Add Motorcycle'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="ID" value={motorcycle.id} onChange={handleChange} />
          <input type="text" name="make" placeholder="Make" value={motorcycle.make} onChange={handleChange} />
          <input type="text" name="model" placeholder="Model" value={motorcycle.model} onChange={handleChange} />
          <input type="number" name="year" placeholder="Year" value={motorcycle.year} onChange={handleChange} />
          <input type="text" name="engineType" placeholder="Engine Type" value={motorcycle.engineType} onChange={handleChange} />
          <input type="text" name="color" placeholder="Color" value={motorcycle.color} onChange={handleChange} />
          <input type="number" name="price" placeholder="Price" value={motorcycle.price} onChange={handleChange} />
          <input type="number" name="mileage" placeholder="Mileage" value={motorcycle.mileage} onChange={handleChange} />
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addMotorcycle}>Add Motorcycle</button>
          ) : (
            <>
              <button className="btn-green" onClick={updateMotorcycle}>Update Motorcycle</button>
              <button className="btn-gray" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      <div>
        <h3>Get Motorcycle By ID</h3>
        <input
          type="number"
          value={idToFetch}
          onChange={(e) => setIdToFetch(e.target.value)}
          placeholder="Enter ID"
        />
        <button className="btn-blue" onClick={getMotorcycleById}>Fetch</button>

        {fetchedMotorcycle && (
          <div>
            <h4>Motorcycle Found:</h4>
            <pre>{JSON.stringify(fetchedMotorcycle, null, 2)}</pre>
          </div>
        )}
      </div>

      <div>
        <h3>All Motorcycles</h3>
        {motorcycles.length === 0 ? (
          <p>No motorcycles found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {Object.keys(motorcycle).map((key) => (
                    <th key={key}>{key}</th>
                  ))}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {motorcycles.map((bike) => (
                  <tr key={bike.id}>
                    {Object.keys(motorcycle).map((key) => (
                      <td key={key}>{bike[key]}</td>
                    ))}
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => handleEdit(bike)}>Edit</button>
                        <button className="btn-red" onClick={() => deleteMotorcycle(bike.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

    </div>
  );
};

export default MotorcycleManager;
