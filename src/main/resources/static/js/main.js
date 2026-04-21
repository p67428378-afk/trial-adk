import { policyService } from './policyService.js';

document.addEventListener('DOMContentLoaded', () => {
    loadPolicyDetails(1); // Load details for a sample policy ID
    loadNotifications();
    loadCoverageQuickView();
});

async function loadPolicyDetails(policyId) {
    const policyDetailsContent = document.getElementById('policy-details-content');
    policyDetailsContent.innerHTML = 'Loading policy details...';

    const policy = await policyService.getPolicyById(policyId);

    if (policy) {
        policyDetailsContent.innerHTML = `
            <div class="grid grid-cols-2 gap-4 text-on-surface-variant text-sm">
                <div>
                    <p class="font-semibold text-on-surface">Policy Number:</p>
                    <p>${policy.policyNumber}</p>
                </div>
                <div>
                    <p class="font-semibold text-on-surface">Coverage Type:</p>
                    <p>${policy.coverageType}</p>
                </div>
                <div>
                    <p class="font-semibold text-on-surface">Effective Date:</p>
                    <p>${policy.effectiveDate}</p>
                </div>
                <div>
                    <p class="font-semibold text-on-surface">Expiration Date:</p>
                    <p>${policy.expirationDate}</p>
                </div>
                <div>
                    <p class="font-semibold text-on-surface">Premium Amount:</p>
                    <p>$${policy.premiumAmount}</p>
                </div>
                <div>
                    <p class="font-semibold text-on-surface">Status:</p>
                    <p>${policy.status}</p>
                </div>
            </div>
            <h3 class="text-xl font-bold text-on-surface mt-6 mb-4">Beneficiaries</h3>
            <ul class="list-disc pl-5 text-on-surface-variant text-sm">
                ${policy.beneficiaries.map(b => `
                    <li>${b.name} (${b.relationshipType}, DOB: ${b.dateOfBirth})</li>
                `).join('')}
            </ul>
        `;
    } else {
        policyDetailsContent.innerHTML = '<p class="text-red-500">Failed to load policy details.</p>';
    }
}

function loadNotifications() {
    const notificationsContent = document.getElementById('notifications-content');
    // Static content for now, could be dynamic from an API
    notificationsContent.innerHTML = `
        <div class="mb-4 p-4 bg-surface-container rounded-lg">
            <p class="font-semibold text-on-surface">Upcoming Premium Due</p>
            <p class="text-sm text-on-surface-variant">Your premium of $150 is due on Oct 15, 2023.</p>
        </div>
        <div class="mb-4 p-4 bg-surface-container rounded-lg">
            <p class="font-semibold text-on-surface">Policy Update Available</p>
            <p class="text-sm text-on-surface-variant">New coverage options are available for your auto policy.</p>
        </div>
    `;
}

function loadCoverageQuickView() {
    const coverageQuickViewContent = document.getElementById('coverage-quick-view-content');
    // Static content for now, could be dynamic from an API
    coverageQuickViewContent.innerHTML = `
        <div class="grid grid-cols-2 gap-4">
            <div class="bg-surface-container p-4 rounded-lg">
                <p class="text-sm text-on-surface-variant">Auto Coverage</p>
                <p class="font-semibold text-on-surface">$50,000</p>
            </div>
            <div class="bg-surface-container p-4 rounded-lg">
                <p class="text-sm text-on-surface-variant">Home Coverage</p>
                <p class="font-semibold text-on-surface">$200,000</p>
            </div>
        </div>
    `;
}
