const API_BASE_URL = 'http://localhost:8080/api/v1/policies'; // Assuming Spring Boot runs on 8080

export const policyService = {
    async getAllPolicies(skip = 0, limit = 100) {
        try {
            const response = await fetch(`${API_BASE_URL}?skip=${skip}&limit=${limit}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return await response.json();
        } catch (error) {
            console.error("Error fetching policies:", error);
            return [];
        }
    },

    async getPolicyById(policyId) {
        try {
            const response = await fetch(`${API_BASE_URL}/${policyId}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return await response.json();
        } catch (error) {
            console.error(`Error fetching policy with ID ${policyId}:`, error);
            return null;
        }
    },

    async createPolicy(policyData) {
        try {
            const response = await fetch(`${API_BASE_URL}/`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(policyData),
            });
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return await response.json();
        } catch (error) {
            console.error("Error creating policy:", error);
            return null;
        }
    },

    async updatePolicy(policyId, policyData) {
        try {
            const response = await fetch(`${API_BASE_URL}/${policyId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(policyData),
            });
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return await response.json();
        } catch (error) {
            console.error(`Error updating policy with ID ${policyId}:`, error);
            return null;
        }
    },

    async deletePolicy(policyId) {
        try {
            const response = await fetch(`${API_BASE_URL}/${policyId}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return true;
        } catch (error) {
            console.error(`Error deleting policy with ID ${policyId}:`, error);
            return false;
        }
    }
};
